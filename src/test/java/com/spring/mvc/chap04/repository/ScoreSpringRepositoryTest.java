package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ScoreSpringRepositoryTest {

    @Autowired
    ScoreRepository repository;

    @Test
    void saveTest() {
        //given
        ScoreRequestDTO dto = new ScoreRequestDTO();
        dto.setName("냥냥이");
        dto.setKor(99);
        dto.setEng(88);
        dto.setMath(77);
        //when
        repository.save(new Score(dto));
        //then
    }



}