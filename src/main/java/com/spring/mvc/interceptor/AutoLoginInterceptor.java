package com.spring.mvc.interceptor;

import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import com.spring.mvc.chap05.service.MemberService;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class AutoLoginInterceptor implements HandlerInterceptor {

    private final MemberMapper memberMapper;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 사이트 재방문 했을 때
        // 1. 자동로그인 쿠키를 탐색 -> 요청(request)정보에서 찾을 수 있음
//        Cookie[] cookies = request.getCookies(); -> 반복문 돌려서 찾음
        // 한방에 찾아주는 게 있음
        Cookie c = WebUtils.getCookie(request, LoginUtil.AUTO_LOGIN_COOKIE);

        if (c != null) {
            // 2. 쿠키에 저장된 세션아이디(name: auto, 벨류값: 긴 것)를 읽는다.
            String sessionId = c.getValue();

            // 3. DB에서 세션아이디로 회원정보를 조회한다. -> Mapper.xml에 만든 것 그거 있는거 주입받아서 가져오기 MemberMapper
            Member foundMember = memberMapper.findMemberByCookie(sessionId);

            // 4. 회원이 조회가 되었고, 자동로그인 만료일 이전이라면
            if (foundMember != null && LocalDateTime.now().isBefore(foundMember.getLimitTime())) {

                // 5. 로그인 처리 maintainLoginState -> MemberService 주입받기
                memberService.maintainLoginState(request.getSession(), foundMember.getAccount());
            }

        }

        return true;
    }
}
