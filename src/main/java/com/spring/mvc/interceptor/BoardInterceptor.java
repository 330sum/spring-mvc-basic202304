package com.spring.mvc.interceptor;

// 인터셉터: 하위 컨틀로러에 요청이 들어가기 전/후에 공통으로 검사할 일들을 정의해 놓는 클래스
// 게시판 관련 인가 처리

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mvc.util.LoginUtil.isMine;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {

    private final BoardMapper boardMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 로그인을 했는지 확인할 것임
        // 로그인을 안했으면 로그인페이지로 강제로 리다이렉션 할 것임
        // ! 주의할 것
        HttpSession session = request.getSession();
        if(!LoginUtil.isLogin(session)) {
            log.info("this request( {} ) denied!", request.getRequestURI());
            response.sendRedirect("/members/sign-in");
            return false;
        }
        // interseptor설정 필요함 -> config -> InterceptorConfig 클래스 생성


        // 삭제요청을 한다면 자기가 쓴 글인지 체크 또는 관리자인지 체크
        // 관리자인지 확인 하기 -> 세션 체크 -> LoginUtil에서 만들기
        /*
            1. 우선 로그인 계정과 삭제하려는 게시물의 계정명이 일치해야함
            2. 로그인계정은 세션에서 구할 수 있음
            3. 삭제하려는 게시물의 계정은 어케구함?
                -> 삭제 요청에는 글번호 정보가 있다.
                -> 글번호를 DB에 조회해서 계정명을 얻어낸다.
        * */
        // 삭제요청인지 확인
        String uri = request.getRequestURI();
        if (uri.contains("delete")) {

            // 쿼리 파라미터 읽기
            int bno = Integer.parseInt(request.getParameter("bno"));
            // BoardMapper로 보내야 하니까 주입받기

            // 게시물 정보 읽기
            Board board = boardMapper.findOne(bno);

            String targetAccount = board.getAccount();

            // 관리자면 통과
            if (LoginUtil.isAdmin(session)) return true;

            // 내 글이 아니면 통과못하게
            if (!isMine(session, targetAccount)) {
                response.sendRedirect("/access-deny");
                return false;
            }

        }



        // return true면 들여보내고, false면 내보냄
        log.info("board interceptor pass!");
        return true;
    }


}
