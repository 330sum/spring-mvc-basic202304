package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonSpringRepositoryTest {

    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest() {
        // given
        Person p = new Person();
        p.setPersonName("김스프링");
        p.setPersonAge(2);
        // when
        repository.savePerson(p);
    }

    @Test
    void removePersonTest() {
        // given
        long id = 4L;
        // when
        repository.removePerson(id);
    }

    @Test
    void modifyPersonTest() {
        // given
        Person p = new Person();
        p.setId(5L);
        p.setPersonName("콩콩이");
        p.setPersonAge(1000);
        // when
        boolean flag = repository.modify(p);
        // then
        assertTrue(flag);
    }

    @Test
    void findAllTest() {
        List<Person> people = repository.findAll();
        for (Person p : people) {
            System.out.println("p = " + p);
        }
    }

    @Test
    void findOneTest() {
        Person p = repository.findOnd(6L);
        System.out.println("p = " + p);

        assertEquals("냥냥이", p.getPersonName());
    }


}