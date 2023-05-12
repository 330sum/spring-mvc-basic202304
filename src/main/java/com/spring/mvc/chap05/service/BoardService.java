package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.chap05.repository.BoardRepository;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

//    private final BoardRepository boardRepository;
    private final BoardMapper boardRepository;


    // 중간처리 기능 자유롭게!

    /* 목록 띄우기 중간처리 */
    public List<BoardListResponseDTO> getList(Search page) {

        return boardRepository.findAll(page)
                .stream()
                .map(board -> new BoardListResponseDTO(board))
                .collect(Collectors.toList())
                ;

        // 만약 람다 너무어려우면 alt+enter -> 스트림API체인을 루프로 바꾸기

//        List<BoardListResponseDTO> list = new ArrayList<>();
//        for (Board board : boardRepository.findAll()) {
//            BoardListResponseDTO boardListResponseDTO = new BoardListResponseDTO(board);
//            list.add(boardListResponseDTO);
//        }
//        return list;
    }

    /* 글 등록 중간처리 */
    public boolean register(BoardWriteRequestDTO dto, HttpSession session) {
        Board board = new Board(dto);
//        board.setAccount("현재로그인한계정");
        // 로그인한 사람의 계정정보 추가 (세션) - 세션은 컨트롤러한테 달라고 해서 매퍼에게 저장하기
        board.setAccount(LoginUtil.getCurrentLoginMemberAccount(session));
        return boardRepository.save(board);
    }

    /* 삭제 중간 처리 */
    public boolean delete(int bno) {
        return boardRepository.deleteByNo(bno);
    }

    /* 글 상세보기 중간처리 */
    public BoardDetailResponseDTO getDetail(int bno) {

        Board board = boardRepository.findOne(bno);
        // 조회수 상승 처리
//        board.setViewCount(board.getViewCount() + 1);
        boardRepository.upViewCount(bno);

        return new BoardDetailResponseDTO(board);
    }

    public int getCount(Search search) {
        return boardRepository.count(search);
    }

    /* 수정 중간 처리 */
//    public boolean update (int bno) {
//        return boardRepository.update(bno);
//    }



}
