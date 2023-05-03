package com.spring.mvc.chap51.entity;

import com.spring.mvc.chap51.dto.HealthRequestDTO;
import lombok.*;

import java.time.LocalDateTime;
    /*
    create table tbl_health (
	mem_num INT(10) auto_increment primary key,
	name VARCHAR(10) not null,
	gender CHAR(1),
	tall DOUBLE(5, 2),
	weight DOUBLE(5, 2),
	bmi DOUBLE(5, 2),
	obesity VARCHAR(10),
	reg_date DATETIME default current_timestamp,
);
    * */

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Health {

    private int memNum;
    private String name;
    private Gender gender;
    private int age;
    private double tall;
    private double weight;
    private double bmi;
    private Obesity obesity; // 비만도
    private LocalDateTime regDate;


    public Health(HealthRequestDTO healthRequestDTO) {
        this.name = healthRequestDTO.getName();
        this.age = healthRequestDTO.getAge();
    }
}
