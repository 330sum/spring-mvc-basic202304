package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* URL 요청
 *  목록조회
 * /board/list : GET
 *  게시글등록
 * /board/writer : POST
 *  게시글삭제
 * /board/remove : GET
 *  상세조회
 * /board/detail : GET
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 목록
    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("/board/list : GET");
        List<BoardListResponseDTO> responseDTOS = boardService.getList();
        model.addAttribute("bList", responseDTOS);

//        List<Board> boardList = boardService.getList();
//        model.getAttribute("boardList", boardList);
        return "chap05/list";
    }

    // 글쓰기 화면 조회 요청
    @GetMapping("/write")
    public String write() {
        System.out.println("/board/write : GET");
        return "chap05/write";
    }

    // 글 등록 요청 처리
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto) {
        System.out.println("/board/write : POST");
        boardService.register(dto);
        return "redirect:/board/list";
    }
//    // 등록
//    @PostMapping("/register")
//    public String register() {
//        System.out.println("/board/register : POST");
//        return "";
//    }



    // 삭제
    @GetMapping("/remove")
    public String remove(@RequestParam(required = true) int boardNo) {
        System.out.println("/board/remove : GET");
        boardService.delete(boardNo);
        return "redirect:/board/list";
    }



    // 상세보기
    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) int boardNo, Model model) {
        System.out.println("/board/detail : GET");

        retrieve(boardNo, model);

        return "chap05/detail";
    }

    private void retrieve(int boardNo, Model model) {
        Board board = boardService.retrieve(boardNo);
        model.addAttribute("board", board);
    }


}
