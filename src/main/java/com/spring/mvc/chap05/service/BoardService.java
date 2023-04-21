package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 중간처리 기능 자유롭게!

    // 중간처리 기능 자유롭게 사용
    /* 목록 띄우기 중간처리 */
    public List<BoardListResponseDTO> getList() {

        return boardRepository.findAll()
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(Collectors.toList())
                ;
    }

    /* 글 등록 중간처리 */
    public boolean register(BoardWriteRequestDTO dto) {
        return boardRepository.save(new Board(dto));
    }

    /* 삭제 중간 처리 */
    public boolean delete(int boardNo) {
        return boardRepository.deleteByNo(boardNo);
    }


    /* 상세보기 중간 처리*/
    public Board retrieve (int boardNo){
        return boardRepository.findOne(boardNo);
    }

}
