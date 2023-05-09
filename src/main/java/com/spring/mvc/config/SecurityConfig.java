package com.spring.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
@EnableWebSecurity // 웹보안 관련 설정을 이 파일을 기반으로 적용 (@Configuration 포함됨)
public class SecurityConfig {

    // 비밀번호 암호화 객체 빈등록
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티 기본 설정을 처리하는 빈 등록
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 시큐리티 설치시 초기에 뜨는 강제 인증을 해제
        http.csrf().disable() // csrf토큰공격 방어기능 해제
                .authorizeHttpRequests()
                .antMatchers("/**")
                .permitAll();

        return http.build();
    }

}
