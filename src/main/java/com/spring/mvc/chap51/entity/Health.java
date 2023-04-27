package com.spring.mvc.chap51.entity;

import com.spring.mvc.chap51.dto.HealthRequestDTO;
import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Health {

    int memNum;

    String name;
    Gender gender;
    int age;
    double tall;
    double weight;

    double bmi;
    Obesity obesity; // 비만도


    public Health(HealthRequestDTO healthRequestDTO) {
        this.name = healthRequestDTO.getName();
        this.age = healthRequestDTO.getAge();
    }
}
