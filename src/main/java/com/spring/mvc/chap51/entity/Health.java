package com.spring.mvc.chap51.entity;

import com.spring.mvc.chap51.dto.HealthRequestDTO;
import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Health {

    /*
    create table tbl_health (
	mem_num INT(10) auto_increment primary key,
	name VARCHAR(10) not null,
	gender CHAR(1),
	tall FLOAT(5, 2),
	weight FLOAT(5, 2),
	bmi FLOAT(5, 2),
	obesity VARCHAR(10)
);
    * */
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
