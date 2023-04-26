package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {

    // 클라이언트가 주는 데이터
    private String name; // 이름
    private int kor, eng, math; // 국어점수, 영어점수, 수학점수

    // 서버가 계산하는 데이터
    private int stuNum; // 학번
    private int total; // 총점
    private double average; // 평균
    private Grade grade; // 학점

    /* dto를 전달받는 생성자 */
    public Score(ScoreRequestDTO dto) {
        this.name = dto.getName();
        changeScore(dto);
    }

    public Score(ResultSet rs) throws SQLException {
        this.stuNum = rs.getInt("stu_num");
        this.name = rs.getString("name"); // column은 DB에서 매칭 잘 해서 가져올 것!
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.total= rs.getInt("total");
        this.average = rs.getDouble("average");
        this.grade = Grade.valueOf(rs.getString("grade")); // DB에서 읽어와서 자바에서 enum으로 변환
    }

    public void changeScore(ScoreRequestDTO dto) {
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        calcTotalAndAvg(); // 총점, 평균  [ * 메서드 추출 - ctrl + alt + m ]
        calcGrade(); // 학점 계산
    }

    private void calcTotalAndAvg() {
        this.total = kor + eng + math;
        // 소수점 이하 2자리
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedAvg = df.format(total / 3.0);
        this.average = Double.parseDouble(formattedAvg);
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
