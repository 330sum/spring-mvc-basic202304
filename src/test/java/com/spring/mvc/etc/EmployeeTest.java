package com.spring.mvc.etc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void tttt() {
        Employee emp = new Employee();
        Employee e = Employee.builder()
                .position("대리")
                .empName("홍길동")
                .hireDate(LocalDate.of(2019, 3,15))
                .build();

        System.out.println("e = " + e);


        Actor actor =Actor.builder()
                .actorName("장동건")
                .hasPhone(false)
                .actorAge(40)
                .build();


    }

}