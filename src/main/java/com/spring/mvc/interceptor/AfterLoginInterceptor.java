package com.spring.mvc.interceptor;

import com.spring.mvc.util.LoginUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 로그인 이후 비회원 관련 페이지 진입 차단
@Configuration
public class AfterLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        // 로그인 했다면 비회원관련페이지 (회원가입, 로그인창 등) 못 들어가게 하기
        if (LoginUtil.isLogin(session)) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
