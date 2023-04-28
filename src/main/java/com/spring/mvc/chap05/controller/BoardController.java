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

@Controller // DispatcherServlet이 컨트롤러 주입받을 수 있도록 꼭 작성!
@RequiredArgsConstructor
@RequestMapping("/board") // 공통 URL
public class BoardController {

    private final BoardService boardService;

    // 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("/board/list : GET");
        List<BoardListResponseDTO> responseDTO = boardService.getList();
        model.addAttribute("bList", responseDTO);

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


    // 글 삭제 요청 처리
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("/board/delete : GET");
        boardService.delete(bno);
        return "redirect:/board/list";
    }



    // 글 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int bno, Model model) {
        System.out.println("/board/detail : GET");
        model.addAttribute("b", boardService.getDetail(bno));
        return "chap05/detail";
    }


//    // 글 수정화면 열어주기
//    @GetMapping("/update")
//    public String update(int bno, Model model) {
//        System.out.println("/board/update : GET");
//        model.addAttribute("b",boardService.getDetail(bno));
//        return "chap05/modify-form";
//    }


    // 글 수정 확인
//    @PostMapping("/update")


}
