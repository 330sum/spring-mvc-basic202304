package com.spring.mvc.chap05.api;

import com.spring.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.request.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.request.ReplyModifyRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j

// 클라이언트의 접근을 어떤 app에서만 허용
@CrossOrigin(origins  ={"http://127.0.0.1:5501"})
public class ReplyController {

    private final ReplyService replyService;

    /* 댓글 목록 조회 요청 */
    // URL : /api/v1/replies/3/page/1 -- 3번게시물의 댓글 1페이지
    @GetMapping("/{boardNo}/page/{pageNo}")
    public ResponseEntity<?> list(
            @PathVariable long boardNo,
            @PathVariable int pageNo
    ) {
        log.info("/api/v1/replies/{}/page/{} : GET!!", boardNo, pageNo);

        Page page = new Page();
        page.setPageNo(pageNo);
        page.setAmount(10);
        ReplyListResponseDTO replyList = replyService.getList(boardNo, page);
//        List<Reply> replyList = replyService.getList(boardNo, page);
        return ResponseEntity.ok().body(replyList);

    }


    /* 댓글 등록 요청 (댓글 써줘! -> DB에 넣어줘 post) */
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody ReplyPostRequestDTO dto, BindingResult result) {
                // CSR(클라이언트사이드렌더링)은 등록을 하려면 post로 클라이언트가 서버로 post로 보내야하는데, post 보내려면 form으로 보내야함. form은 html임.
                // @RequestBody : 요청메시지 바디에 JSON으로 보내주세융
                // @Validated : DTO에 검증 @필수값 넣은 것 대로 검증함
                // BindingResult result : 검증결과를 가진 객체

        // 입력값 검증에 걸리면 4xx 상태 코드 리턴
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("쥭을래? 값 다 안보냄"+result.toString());
        }


        log.info("api/v1/replies : POST!");
        log.info("param: {}", dto); // 로그도 dto데이터 잘 넘어오는지 서버에서 찍기 (debug로 찍는게 더 좋음) - 데이터레벨은 디버그에서 찍음, 인포는 운영서버에서 찍음
        // 서비스에 위임안되도 아래 로그로 볼수 있음


        // 서비스에 비즈니스 로직 처리 위임
        try {
            ReplyListResponseDTO responseDTO = replyService.register(dto);//ctrl + alt + t
        // 성공시 클라이언트에게 응답하기
        //  return ResponseEntity.ok().body("OK!!!");
            return ResponseEntity.ok().body(responseDTO);

        } catch (Exception e) {
            // 문제발생 상황을 클라이언트에게 전달
            log.warn("500 Status code response!! caused by: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
            //internalServerError(디비에저장못함 500번에러),  e.getMessage() -> 서비스에 적어둔 메시지
        }

    }


    /* 댓글 삭제 요청 */
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?> remove(@PathVariable Long replyNo
                                // @PathVariable("replyNo") long rno
                                // long(x), Long(o) -> 기본값 0이 안잡히게 하려고!
    ) {
            if (replyNo == null) {
                return ResponseEntity.badRequest().body("댓글 번호 보내주세요!");
            }

        log.info("/api/v1/replies/{} DELETE!", replyNo);
//        log.info("/api/v1/replies/{} DELETE!", rno);

        try {
            ReplyListResponseDTO responseDTO = replyService.delete(replyNo);
            return ResponseEntity.ok().body(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }



    /* 댓글 수정요청 */
    // Put은 전체수정, 그냥 DTO 넣어도 됨
    // Path는 일부 수정, 파라미터에 @PathVariable
    // 실무에서는 아래처럼 두개 다 받을 수 있도록 만듦
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> modify(
            @Validated @RequestBody ReplyModifyRequestDTO dto
            , BindingResult result
    ) {

        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }

        log.info("/api/v1/replies PUT!");
        try {
            ReplyListResponseDTO responseDTO = replyService.modify(dto);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            log.warn("500 status code! caused by: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }



}
