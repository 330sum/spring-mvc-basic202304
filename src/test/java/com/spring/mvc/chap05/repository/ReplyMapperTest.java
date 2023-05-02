package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {

    @Autowired
    BoardMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;

//    @Test
//    @DisplayName("게시물 300개를 등록하고 각 게시물에 랜덤으로 1000개의 댓글을 나눠서 등록해야 한다")
//    void bulkInsertTest() {
//
//        for (int i = 1; i <= 300; i++) {
//            Board b = Board.builder()
//                    .title("재미있는게시물" + i)
//                    .content("노잼 게시물 내용" + i)
//                    .build();
//            boardMapper.save(b);
//        }
//        assertEquals(300, boardMapper.count(new Search()));
//
//        for (int i = 1; i <= 1000; i++) {
//            Reply r = Reply.builder()
//                    .replyWriter("잼민이" + i)
//                    .replyText("말똥이~~" + i)
//                    .boardNo((long) (Math.random() * 300 + 1))
//                    .build();
//            replyMapper.save(r);
//
//        }
//    }


    @Test
    @DisplayName("댓글을 3번 게시물에 등록하면 3번 게시물의 총 댓글 수는 8개여야 한다")
    @Transactional
    @Rollback // 테스트 끝난 이후 롤백해라 (select는 상관없지만, insert, update, delete는 꼭 롤백!)
    void saveTest() {

        //given
        long boardNo = 3L;
        Reply newReply = Reply.builder()
                .replyText("세이브~~")
                .replyWriter("순구한 순구")
                .boardNo(boardNo)
                .build();

        //when
        boolean flag = replyMapper.save(newReply);

        //then
        assertTrue(flag); // 세이브가 성공했을 것이라고 단언
        assertEquals(8, replyMapper.count(boardNo));
    }


    @Test
    @DisplayName("댓글번호가 1001번인 댓글을 삭제하면 3번게시물의 총 댓글수가 6이어야 한다")
    @Transactional @Rollback
    void  removeTest() {
        //given
        long replyNo = 1001L;
        long boardNo = 3L;

        //when
        boolean flag = replyMapper.deleteOne(replyNo);

        //then
        assertTrue(flag);
        assertEquals(6, replyMapper.count(boardNo));
    }


    @Test
    @DisplayName("댓글번호 1001번을 조회하면 null이 아닐 것이다")
    void findOneTest() {
        //given
        long replyNo = 1001L;
        //when
        Reply replyOne = replyMapper.findOne(replyNo);
        //then
        assertNotNull(replyNo);
    }

    @Test
    @DisplayName("3번 게시물의 댓글 수는 7일 것이다")
    void countTest() {
        //given
        long boardNo = 3L;
        //when
        int count = replyMapper.count(boardNo);
        //then
        assertEquals(7, replyMapper.count(boardNo));
    }

    @Test
    @DisplayName("3번게시물의 1001번 댓글은 수정되어야 한다")
    @Transactional @Rollback
    void modifyTest() {
        //given
        long replyNo = 1001L;
        Reply reply = Reply.builder()
                .replyNo(replyNo)
                .replyWriter("구수한 순구")
                .replyText("냠냠")
                .build();
        //when
        boolean flag = replyMapper.modify(reply);
        //then
        assertTrue(flag);

    }

    @Test
    @DisplayName("999번 댓글의 내용을 수정한 후 다시 조회했을 때 제목이 수정된 제목이어야 한다.")
    @Transactional @Rollback
    void modifyTest2() {
        //given
        long replyNo = 999L;
        String newReplyText = "수정댓그을";
        Reply r = Reply.builder()
                .replyText(newReplyText)
                .replyNo(replyNo)
                .build();
        //when
        boolean flag = replyMapper.modify(r);
        //then
        assertTrue(flag);
        assertEquals(newReplyText, replyMapper.findOne(replyNo).getReplyText());
    }

    @Test
    @DisplayName("3번 게시물의 댓글 목록을 조회했을 때 리스트의 크기가 7이고, 0번 인덱스의 댓글작성자가 잼민이63여야 한다.")
    void findAllTest() {
        //given
        long boardNo = 3L;
        //when
        Page page = new Page();
        page.setAmount(10);
        List<Reply> replyList = replyMapper.findAll(boardNo, page);
        //then
        assertEquals(7, replyList.size());
        assertEquals("잼민이63", replyList.get(0).getReplyWriter());
        assertEquals(63, replyList.get(0).getReplyNo());
    }






//    @Test
//    @DisplayName("댓글번호가 1001번인 댓글을 수정하면 ")

//    @Test
//    @DisplayName("3번게시물의 댓글을 조회하면 7개의 댓글이 있어야 한다")
//    void findAllTest() {
//        //given
//
//        long boardNo = 3L;
//
//        replyMapper.findAll(boardNo, )
//        //when
//        //then
//    }











}