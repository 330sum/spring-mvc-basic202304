package com.spring.mvc.chap05.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징기능 - 부모
// 검색기능 - 자식
@Getter @ToString
@Setter // 검색했을때 세터로 받기때문

public class Search extends Page {

    // 검색 타입, 검색 키워드
    private String type;
    private String keyword;

    // 만약 검색 안하면 초기값 널임. 그렇기에 기본생성자에 빈문자열 넣어주기.
    public Search() {
        this.type = "";
        this.keyword ="";
    }


}
