package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        memberService.join(dto);
    }

}