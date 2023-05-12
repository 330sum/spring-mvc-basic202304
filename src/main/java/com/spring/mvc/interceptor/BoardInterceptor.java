package com.spring.mvc.interceptor;

// 인터셉터: 하위 컨틀로러에 요청이 들어가기 전/후에 공통으로 검사할 일들을 정의해 놓는 클래스
// 게시판 관련 인가 처리

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
@Slf4j
public class BoardInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 로그인을 했는지 확인할 것임
        // 로그인을 안했으면 로그인페이지로 강제로 리다이렉션 할 것임
        if(!LoginUtil.isLogin(request.getSession())) {
            log.info("this request( {} ) denied!", request.getRequestURI());
            response.sendRedirect("/members/sign-in");
            return false;
        }
        // interseptor설정 필요함 -> config -> InterceptorConfig 클래스 생성

        // 삭제요청을 한다면 자기가 쓴 글인지 체크
        // 1. 로그인한 계정 가져오기
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        // 2. 게시글의 계정 가져오기
        request.get

        // 3. 두 계정 비교해서 동일하면 삭제가능하게 해주기
        if(LoginUtil.isLogin(request.getSession())!= )



        // return true면 들여보내고, false면 내보냄
        log.info("board interceptor pass!");
        return true;
    }
}
