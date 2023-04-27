package com.spring.mvc.chap51.dto;

import com.spring.mvc.chap51.entity.Gender;
import lombok.*;

@Setter @Getter
@ToString @NoArgsConstructor
@AllArgsConstructor @EqualsAndHashCode
public class HealthRequestDTO {
    String name;
    Gender gender;
    int age;
    double tall;
    double weight;
}
