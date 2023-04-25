package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 중간처리 기능 자유롭게!

    /* 목록 띄우기 중간처리 */
    public List<BoardListResponseDTO> getList() {

        return boardRepository.findAll()
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
    public boolean register(BoardWriteRequestDTO dto) {
        return boardRepository.save(new Board(dto));
    }

    /* 삭제 중간 처리 */
    public boolean delete(int bno) {
        return boardRepository.deleteByNo(bno);
    }

    /* 글 상세보기 중간처리 */
    public BoardDetailResponseDTO getDetail(int bno) {

        Board board = boardRepository.findOne(bno);
        // 조회수 상승 처리
        board.setViewCount(board.getViewCount() + 1);

        return new BoardDetailResponseDTO(board);
    }

}
