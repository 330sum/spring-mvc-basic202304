package com.spring.mvc.chap04.dto;

import lombok.*;

// 현재 DTO에서 세터,게터, 노아그 필수
// 왜? 스프링이 이 DTO 객체 생성할 때, 노아그(기본생성자)와 세터를 사용하기 때문에 (올아그스 안씀)
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScoreRequestDTO {

    private String name; // 이름
    private int kor, eng, math; // 국어점수, 영어점수, 수학점수

}
