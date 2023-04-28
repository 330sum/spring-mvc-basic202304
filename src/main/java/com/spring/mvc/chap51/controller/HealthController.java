package com.spring.mvc.chap51.controller;

import com.spring.mvc.chap51.dto.HealthRequestDTO;
import com.spring.mvc.chap51.repository.HealthMapper;
import com.spring.mvc.chap51.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/health")
public class HealthController {

    private final HealthService healthService;

    // 전체조회
    @GetMapping("/list")
    public String list() {
        System.out.println("list : GET ");
        return "chap51/health-list";
    }

    // 등록
    @PostMapping("/register")
    public String register(HealthRequestDTO dto) {
        System.out.println("register : POST ");
        return "";
    }

    // 삭제
    @GetMapping("/remove")
    public String remove(int memNum) {
        System.out.println("remove : GET ");

        return "";
    }


    // 개별조회
    @GetMapping("detail")
    public String detail(int memNum) {
        System.out.println("detail : POST ");
        return "";
    }

    // 수정


}
