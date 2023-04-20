package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;

import java.util.List;

// 역할 명세 (추상화):
// 성적 정보를 잘 조회하고, 저장(추가)하고, 삭제하고, 수정해야 한다.
// 그래서 어디에서 조회? 어디에 추가? 어디에서 삭제? 어디에서 수정?
public interface ScoreRepository {

    // 1. 성적정보 전체목록 조회
    List<Score> findAll(); // 일반 목록조회
    default List<Score> findAll(String sort) {
        return null;
    }; // 정렬 목록조회
    // 처음 설계와 다르게 갑자기 인터페이스가 추가되면 선택적으로 오버라이딩 가능하게 default를 붙임 (실무 꿀팁)
    // default 붙이면 오버라이드 강제를 막을 수 있음

    // 2. 성적정보 등록(추가)
    boolean save(Score score);

    // 3. 성적정보 한개 삭제
    boolean deleteByStuNum(int stuNum);

    // 4. 성적정보 개별 조회
    Score findByStuNum(int stuNum);

    // 5. 성적정보 수정
    boolean update(int stuNum, ScoreRequestDTO dto);
}
