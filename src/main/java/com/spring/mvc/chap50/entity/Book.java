package com.spring.mvc.chap50.entity;

import com.spring.mvc.chap50.entity.Grade;

import javax.swing.plaf.nimbus.State;

// 중고서점
public class Book {
    private int bookNo; // 책 번호
    private String title; // 책 제목
    private String author; // 책 저자
    private int originalPrice; // 책 정가
    private Grade Grade; // 책 상태

    private int count; // 책 개수..?

    private int discountPrice; // 책 상태에 따른 가격 인하
}
