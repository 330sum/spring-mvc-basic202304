package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
// 기능명세
public interface BoardMapper {

    // 게시물 목록 조회
//    List<Board> findAll();

    // 페이징(오버로딩)
    List<Board> findAll(Search page);
    // 자식이니까 데이터 더 많음

    // 게시물 등록 - 새글쓰기폼
    boolean save(Board board);

    // 게시물 삭제
    boolean deleteByNo(int boardNo);

    // 게시물 상세 조회
    Board findOne(int boardNo);

    // 조회수 상승기능
    void upViewCount(int boardNo);

    // 총 게시물 수 조회하기
    int count(Search search);


    // 게시물 수정 -> get요청 수정해줘 -> post 수정시켜줘 (title=? content=?)
//    boolean update(int boardNo);


    // 게시물 검색


}
