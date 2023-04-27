package com.spring.mvc.chap51.repository;

import com.spring.mvc.chap51.entity.Health;

import java.util.List;

public interface HealthRepository {

    // 전체 조회
    List<Health> findAll();

    // 등록
    boolean save(Health health);

    // 삭제
    boolean remove(int memNum);

    // 개별조회
    Health findByMemNum(int memNum);

    // 수정
    boolean update(int memNum);
}
