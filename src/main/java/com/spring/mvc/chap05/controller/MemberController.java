package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import com.spring.mvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public void signUp(SignUpRequestDTO dto) {
        log.info("/members/sign-up POST! - {}", dto);
        boolean flag = memberService.join(dto);
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







}
