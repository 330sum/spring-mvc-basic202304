package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;



    @Test
    @DisplayName("SignupDTO를 전달하면 회원가입에 성공해야 한다.")
    void joinTest() {
        //given
        SignUpRequestDTO dto = new SignUpRequestDTO();
        dto.setAccount("kukukaka2");
        dto.setPassword("lalala2");
        dto.setName("루피2");
        dto.setEmail("aaa2@eee.com");
        //when
        memberService.join(dto, savePath);
    }


    @Test
    @DisplayName("계정명이 abcd1234인 회원의 로그인 시도시 결과검증을 상황별로 수행해야 한다.")
    void loginTest() {
        //given
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setAccount("abcd1234");
        dto.setPassword("aaaa!");

        // when
        LoginResult result = memberService.authenticate(dto);

        // then
        assertEquals(LoginResult.SUCCESS, result);
    }



}