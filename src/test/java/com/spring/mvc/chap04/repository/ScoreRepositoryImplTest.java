package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ScoreRepositoryImplTest {

    ScoreRepository repository = new ScoreRepositoryImpl();

    // 1. 단위테스트 (Unit test)
    // 기능 하나하나에 대해서 테스트 해야함 -> 테스트 시나리오 필요
    // - 단언(Assertion) 기법 (강력하게주장 - 나는 자바왕이 될꺼야!)
    @Test
    @DisplayName("저장소에서 findAll을 호출하면 그 반환된 리스트에는 성적정보가 3개 있어야 한다.")
    void findAllTest() {
        // given-when-then 패턴
        // given: 테스트를 위해 주어질 데이터 (ex. parameter)

        // when: 테스트 실제 상황 (~ 할 때)
        List<Score> scoreList = repository.findAll();

        // then: 테스트 결과 확인 (그러면~)
        System.out.println(scoreList.size() == 3);
        // 로그를 찍는게 아니라, 단언.참일것이라고단언하다(스코어리스트= 3개),
        // 나는 스코어리스트의 사이즈가 3인 것이 참이라고 단언한다
        // static Import -> Assertions 생략가능
//        Assertions.assertTrue(scoreList.size() == 3);
//        assertEquals(5, scoreList.size());

        // 나는 리스트의 첫번째 객체의 이름이 뽀로로라고 단언한다
        assertEquals("뽀로로", scoreList.get(0).getName());
//        assertEquals("삐리리", scoreList.get(0).getName());
    }


    @Test
    @DisplayName("저장소에서 findByStuNum을 호출하여 학번이 2인 학생을 조회하면 그 학생의 국어점수가 33점이고, 이름이 루피어야 한다.")
    void findOneTest() {
        // given
        int stuNum = 2;
        // when
        Score byStuNum = repository.findByStuNum(stuNum);
        // then
        assertEquals(33, byStuNum.getKor());
        assertEquals("루피", byStuNum.getName());
    }



    @Test
    @DisplayName("저장소에서 findByStuNum을 호출하여 학번이 99인 학생을 조회하면 null이 리턴될 것이다.")
    void findOneTest2() {
        // given
        int stuNum = 99;
//        int stuNum = 1;
        // when
        Score byStuNum = repository.findByStuNum(stuNum);
        // then
       assertNull(byStuNum);
    }


    @Test
    @DisplayName("저장소에서 학번이 2인 학생을 삭제한 후에 리스트를 전체 조회해보면 성적리스트의 갯수 2개일 것이고, " +
            "다시 2번 학생을 조회했을 때 null이 반환되어야 한다")
    void deleteTest() {
        // given
        int stuNum = 2;
        // when
        repository.deleteByStuNum(stuNum);
        List<Score> scoreList = repository.findAll();
        Score byStuNum = repository.findByStuNum(2);
        // then
        assertEquals(2, scoreList.size());
        assertNull(byStuNum);

        // 눈으로 봐야 알 고싶을 때, 리스트 돌리기
        scoreList.forEach(s -> System.out.println(s));
        for (Score x : scoreList) {
            System.out.println(x);
        }
    }


    @Test
    @DisplayName("새로운 성적정보를 save를 통해 추가하면 성적리스트의 개수가 4개여야 한다.")
    void saveTest() {
        // given
        Score score = new Score();
        score.setName("대길이");
        score.setKor(100);
        score.setMath(50);
        score.setEng(0);
        // 원래는 학번이 빠져서 테스트가 실패할 줄 알았는데, 학번이 0값으로 들어가서 테스트 성공 나옴.
        // 그래서 구현체(ScoreRepositoryImpl)에서 Score추가할 때, ++sequence 을 적어줘야함! (ScoreRepositoryImpl - 45번라인)

        // when
        boolean flag = repository.save(score);
        List<Score> scoreList = repository.findAll();
        // then
        assertEquals(4, scoreList.size());
        assertTrue(flag);
        assertEquals(4, scoreList.get(scoreList.size() - 1).getStuNum());
    }





}