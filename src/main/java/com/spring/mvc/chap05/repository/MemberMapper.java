package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.request.AutoLoginDTO;
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

    // 자동로그인 관련 속성 추가 기능
    void saveAutoLogin(AutoLoginDTO dto);

    // 쿠키값(세션아이디)으로 회월을 조회하는 기능
    Member findMemberByCookie(String sessionId);




    // 회원수정
    // 회원삭제
}
