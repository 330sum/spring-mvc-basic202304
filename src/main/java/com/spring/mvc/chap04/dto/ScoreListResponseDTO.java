package com.spring.mvc.chap04.dto;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor // final만 골라서 초기화
@Getter @ToString @EqualsAndHashCode
public class ScoreListResponseDTO {

    private final int stuNum;
    private final String maskingName; // 첫 글자 빼고 *처리
    private final double average;
    private final Grade grade;

    // 한번 셋팅되면 그대로 클라이언트에게 가야하기 때문에 final
    // final만 골라서 초기화 @RequiredArgsConstructor
    // final이니까 세터 사용하면 안됨


    // requestDTO (클라이언트 -> 서버로 준 일부 데이터만 저장하는 객체) 게터,세터,노아그 필수
    // responseDTO (서버 -> 클라이언트에게 줄 일부 데이터만 저장하는 객체) 게터,세터,노아그 필수 아님
    // entity (데이터베이스에 들어갈 모든 데이터)


    public ScoreListResponseDTO(Score s) {
        this.stuNum = s.getStuNum();
        this.maskingName = makeMaskingName(s.getName());
        this.average = s.getAverage();
        this.grade = s.getGrade();
    }

    // 첫글자만 빼고 다 *처리 하기
    private String makeMaskingName(String originalName) {
        String maskingName = String.valueOf(originalName.charAt(0));
        for (int i = 1; i < originalName.length(); i++) {
            maskingName += "*";
        }
        return maskingName;
    }



}
