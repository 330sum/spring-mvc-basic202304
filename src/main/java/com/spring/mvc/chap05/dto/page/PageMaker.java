package com.spring.mvc.chap05.dto.page;

import lombok.Getter;
import lombok.ToString;

// 페이징 알고리즘 처리 클래스
@Getter // JSP의 ${}, 마이바티스의 #{}는 게터 필요함
@ToString
public class PageMaker {

    // 한번에 그려낼 페이지 수
    // 1 ~ 5, 6 ~ 10
    // 1 ~ 10, 11 ~ 20
    private static final int PAGE_COUNT = 5;

    // 화면 렌더링시 페이지의 시작값과 끝값
    private int begin, end; // 알고리즘에 의해서 만들어짐 (내부)
    private int finalPage;

    // 이전, 다음 버튼 활성화 여부
    private boolean prev, next; // 알고리즘에 의해서 만들어짐 (내부)


    // 현재 요청 페이지 정보
    private Page page; // 브라우저가 주고 (외부)

    // 총 게시물 수
    private int totalCount; // 디비가 주고 (외부)

    public PageMaker(Page page, int totalCount) {
        this.page = page; // 브라우저가 알려주고
        this.totalCount = totalCount; // 디비가 알려주고
//        this.finalPage = finalPage;
        makePageInfo();
    }

    // 페이지 계산 알고리즘
    private void makePageInfo() {

        // 1. end값 계산
        // 올림처리 (현재 위치한 페이지번호 / 한 화면에 배치할 페이지수 ) *  한 화면에 배치할 페이지 수
        this.end = (int) Math.ceil(page.getPageNo() / (double) PAGE_COUNT) * PAGE_COUNT;

        // 2. begin값 계산
        this.begin = this.end - PAGE_COUNT + 1;

        /*

        - 총 게시물수가 237개고, 한 화면당 10개의 게시물을 배치하고 있다면
          페이지 구간은

          1 ~ 10페이지 구간 : 게시물 100개
          11 ~ 20페이지 구간: 게시물 100개
          21 ~ 24페이지 구간: 게시물 37개

        - 마지막 페이지 구간에서는 보정이 필요함.

        - 마지막 구간 끝페이지 보정 공식:
          올림처리(총 게시물 수 / 한 페이지당 배치할 게시물 수)

         */


        // realEnd 와 finalPage
       /*

       int realEnd = (int) Math.ceil((double) totalCount / page.getAmount());

        // 마지막 페이지 구간에서만 엔드보정이 일어나야 함
        if (realEnd < this.end) this.end = realEnd;

        // 이전 버튼 활성화 여부
        this.prev = begin > 1;

        // 다음 버튼 활성화 여부
        this.next = end < realEnd;

        // finalPage 마지막 페이지
        this.finalPage = totalCount / page.getAmount();

        */



        this.finalPage = (int) Math.ceil((double)totalCount / page.getAmount());

        // 마지막 페이지 구간에서만 엔드보정이 일어나야 함
        if (this.finalPage < this.end) this.end = this.finalPage;

        // 이전 버튼 활성화 여부
        this.prev = begin > 1;

        // 다음 버튼 활성화 여부
        this.next = end < this.finalPage;





    }


}
