package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    // 클라이언트가 주는 데이터
    private String name; // 이름
    private int kor, eng, math; // 국어점수, 영어점수, 수학점수

    // 서버가 계산하는 데이터
    private int stuNum; // 학번
    private int total; // 총점
    private double average; // 평균
    private Grade grade; // 학점

    // dto를 전달받는 생성자
    public Score(ScoreRequestDTO dto) {
        this.name = dto.getName();
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        calcTotalAndAvg(); // 총점, 평균  [ * 메서드 추출 - ctrl + alt + m ]
        calcGrade(); // 학점 계산


    }



    private void calcTotalAndAvg() {
        this.total = kor + eng + math;
        this.average = total / 3.0;
    }


    private void calcGrade() {
        if (average >= 90) {
            this.grade = Grade.A;
        } else if (average >= 80) {
            this.grade = Grade.B;
        } else if (average >= 70) {
            this.grade = Grade.C;
        } else if (average >= 60) {
            this.grade = Grade.D;
        } else {
            this.grade = Grade.F;
        }
    }


}
