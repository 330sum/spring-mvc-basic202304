package com.spring.mvc.chap51.entity;

import com.spring.mvc.chap04.entity.Grade;
import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pscore {
    private String name;
    private int kor, eng, math;

    private int stuNum;
    private int total;
    private double avg;
    private Grade grade;

}
