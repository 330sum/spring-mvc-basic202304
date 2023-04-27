package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper mapper;

    @Test
    @DisplayName("성적 정보를 저장해야한다")
    void saveTest(){
        //given
        Score s = Score.builder()
                .name("무민")
                .kor(100)
                .eng(100)
                .math(100)
                .total(300)
                .average(100)
                .grade(Grade.A)
                .build();
        //when
        boolean flag = mapper.save(s);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("정보를 삭제해야한다")
    void deleteTest() {
        //given
        int stuNum = 4;
        //when
        boolean flag = mapper.deleteByStuNum(stuNum);
        //then
        assertTrue(flag);
    }

//    @Test
//    @DisplayName("전체조회 잘 해야한다~")
//    void findAllTest() {
//        //given
//
//        //when
////        List<Score> all = mapper.findAll(sort);
//        //then
//    }

    @Test
    @DisplayName("개별조회해야한다")
    void findOneTest() {
        //given
        int stuNum=2;
        //when
        Score score = mapper.findByStuNum(stuNum);
        //then
        System.out.println("score = " + score);
        assertEquals("냥냥이", score.getName());
        assertEquals(60,score.getKor());
        assertEquals(70,score.getEng());
        assertEquals(90, score.getMath());
        assertEquals(220, score.getTotal());
        assertEquals(73.33, score.getAverage());
        assertEquals(Grade.C, score.getGrade());
        assertNotNull(score);
    }


}