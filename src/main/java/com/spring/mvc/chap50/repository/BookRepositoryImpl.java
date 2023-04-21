package com.spring.mvc.chap50.repository;

import com.spring.mvc.chap50.entity.Book;

import java.util.List;

public interface BookRepositoryImpl {

    // 중고책 목록 조회
    List<Book> findAll();
    // 중고책 등록
    boolean save(Book book);
    // 중고책 삭제
    boolean deleteByNo(int bookNo);
    // 중고책 상세 조회
    Book findOne(int bookNo);
}
