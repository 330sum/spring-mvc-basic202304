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
    public String signIn(LoginRequestDTO dto, RedirectAttributes ra) {
        // 리다이렉션시 2번째 응답에 데이터를 보내기 위함
        log.info("/members/sign-in POST! - {}", dto);

        LoginResult result = memberService.authenticate(dto);

        // 로그인 성공시
        if (result == LoginResult.SUCCESS) {
            return "redirect:/";
        }

        ra.addFlashAttribute("msg", result);
//        ra.addAttribute("msg", result);


        // 로그인 실패시
        return "redirect:/members/sign-in";
        /*
        // 왜 모델에 담으면 "redirect:/members/sign-in" jsp에 값이 찍히지 않는 이유?
            redirect 재요청 (요청2번) 표사고 업어줌
                model이 첫번째요청인데 http여서 기억못함
                그래서 redirect 새로운 요청(2번째) 이기 때문에 jsp에 안남음
                그래서 Model이 아니라 RedirectAttributes 사용하고, 메서드 불을 때도 addFlashAttribute 사용
            forwarding to jsp 길안내 (요청 1 , 응답 1)



        * */

    }



}
