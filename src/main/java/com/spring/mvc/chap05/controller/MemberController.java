package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import com.spring.mvc.util.LoginUtil;
import com.spring.mvc.util.upload.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mvc.util.LoginUtil.isAutoLogin;

// CSR, SSR 두개다
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {


    @Value("${file.upload.root-path}")
    private String rootPath;
    private final MemberService memberService;

    // 회원가입 요청 -> SSR
    // 회원가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp() {
        log.info("/members/sign-up GET - forwarding to jsp");
        return "members/sign-up";
    }

    // 회원가입 처리 요청
    @PostMapping("/sign-up")
    public String signUp(SignUpRequestDTO dto) {
        log.info("/members/sign-up POST ! - {}", dto);

        MultipartFile profileImage = dto.getProfileImage();
        log.info("프로필사진 이름: {}", profileImage.getOriginalFilename());

        // 첨부파일 없으면 그냥 null로 들어가게 하기 (억지로 0바이트 파일 만들지 못하게)
        String savePath = null;
        if (!profileImage.isEmpty()) {
            // 실제 로컬 스토리지에 파일을 업로드하는 로직
            // 첨부파일이 있을때만 업로드
            savePath = FileUtil.uploadFile(profileImage, rootPath);
        }

        boolean flag = memberService.join(dto, savePath);

        return "redirect:/members/sign-in";
    }

    // 아이디, 이메일 중복검사
    // 비동기 요청 처리(REST방식)
    @GetMapping("/check")
    @ResponseBody // 이 클래스에서 CSR, SSR 다 사용중, restcontroller 못 사용하니까 여기서 이거 꼭 써주기
    public ResponseEntity<?> check(String type, String keyword) {
        log.info("/members/check?type={}&keyword={} ASYNC GET!", type, keyword);

        boolean flag = memberService.checkSignUpValue(type, keyword);
        return ResponseEntity.ok().body(flag);

    }


    // 로그인 양식 요청
    @GetMapping("/sign-in")
    public String signIn(HttpServletRequest request) {
        log.info("/members/sign-in GET - forwarding to jsp");

        // 요청정보 헤더 안에는 Referer라는 키가 있는데,
        // Referer의 값은 이 페이지로 들어올 때, 어디에서 왔는지에 대한 URI 정보가 기록되어 있음
        String referer = request.getHeader("Referer");
        log.info("referer: {}", referer);

        return "members/sign-in";
    }


    // 로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto, RedirectAttributes ra
            , HttpServletResponse response, HttpServletRequest request) {
        // 리다이렉션시 2번째 응답에 데이터를 보내기 위함
        log.info("/members/sign-in POST! - {}", dto);

        LoginResult result = memberService.authenticate(dto, request.getSession(), response);

        // 로그인 성공시
        if (result == LoginResult.SUCCESS) {

            // 서버에서 세션에 로그인 정보를 저장
//            HttpSession session = request.getSession();
//            session.setAttribute("login", "메롱");
            // 근데 이 일 컨트롤러가 하면 싫어하니까 서비스로 보내기
            memberService.maintainLoginState(request.getSession(), dto.getAccount());


//            // 쿠키 만들기("이름","값") - 둘다 스트링밖에 못줌
//            Cookie loginCookie = new Cookie("login", "홍길동");
//            // 쿠키 셋팅
//            loginCookie.setPath("/"); // 쿠키를 들고다니는 곳 (/면 모든 곳)
//            loginCookie.setMaxAge(60 * 60 * 24); // 쿠키수명(초단위)(현재 하루)
//
//            // 쿠키를 응답시에 실어서 클라이언트에게 전송
//            response.addCookie(loginCookie);


            return "redirect:/";
        }

        // 1회용으로 쓰고 버릴 데이터
        ra.addFlashAttribute("msg", result);
//        ra.addAttribute("msg", result);


        // 로그인 실패시
        return "redirect:/members/sign-in";
        /*
        // 모델에 담아서 "redirect:/members/sign-in" 하면 jsp에 값이 찍히지 않는 이유?
            -- redirect 재요청 (요청2번) 표사고 업어줌
                model이 첫번째 요청(명시적 요청)인데 http여서 응답하고 기억못함
                redirect 자동으로 요청(두번째 요청) 된건데, 그 전에꺼 이미 응답하고 남은게 없어서 jsp에 줄수가음슴
                그래서 Model이 아니라 RedirectAttributes 사용하고, 메서드 부를 때도 addFlashAttribute 사용
            -- forwarding to jsp 길안내 (요청 1번, 응답 1번)



        * */

    }

    // 로그아웃 요청 처리
    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        // 로그인 중인지 확인
        if (LoginUtil.isLogin(session)) {

            // 자동 로그인 상태라면 해제한다.
            // 로그인쿠키를 가지고 있는지 확인 필요 -> loginUtil에 자동로그인 여부 확인 메서드 만들기
            if (isAutoLogin(request)) {
                log.info("auto login clearing...");
                memberService.autoLoginClear(request, response);
                // 쿠키 삭제하려면 response객체가 있어야 함 -> 쿠키를 지워줘야하기 때문
                // 서비스쪽에 만들기
            }

            // 세션에서 login정보를 제거
//        HttpSession session = request.getSession();
            session.removeAttribute("login");

            // 세션을 아예 초기화 (세션만료 시간 초기화) invalidate(무효화)
            session.invalidate();

            return "redirect:/";
        }

        // 로그인 중이 아닌데, 로그아웃 요청하면 로그인페이지로 보내기
        return "redirect:/members/sign-in";
    }


}
