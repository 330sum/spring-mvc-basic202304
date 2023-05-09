package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    // 회원가입기능 (INSERT)
    boolean save(Member member);

    // 회원 정보 조회 (SELECT)
    Member findMember(String account);

    // 회원 중복 체크(account, email 검사) 기능 -- SQL 떠올리기
    int isDuplicate(
            @Param("type") String type,
            @Param("keyword") String keyword);

    // 회원수정
    // 회원삭제
}
