package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// CSR, SSR 두개다
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

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
        log.info("/members/sign-up POST! - {}", dto);
        boolean flag = memberService.join(dto);

        return "redirect:/board/list";
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
    public String signIn() {
        log.info("/members/sign-in GET - forwarding to jsp");
        return "members/sign-in";
    }


    // 로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto, RedirectAttributes ra
            , HttpServletResponse response, HttpServletRequest request) {
        // 리다이렉션시 2번째 응답에 데이터를 보내기 위함
        log.info("/members/sign-in POST! - {}", dto);

        LoginResult result = memberService.authenticate(dto);

        // 로그인 성공시
        if (result == LoginResult.SUCCESS) {

            // 서버에서 세션에 로그인 정보를 저장
            HttpSession session = request.getSession();
            session.setAttribute("login", "메롱");




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



}
