package com.spring.mvc.chap51.repository;

import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap51.entity.Pscore;

import java.util.List;

public interface PscoreRepository {

    // 전체조회
    List<Pscore> findAll();
    // 정렬
    List<Pscore> findAll(String sort);
    // 등록
    boolean register(Score score);
    // 삭제
    boolean remove(int stuNum);
    // 상세조회
    Score detail(int stuNum);
    // 수정
    boolean update(int stuNum)

}
