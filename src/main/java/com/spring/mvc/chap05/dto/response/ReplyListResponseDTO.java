package com.spring.mvc.chap05.dto.response;

import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.dto.response.ReplyDetailResponseDTO;
import lombok.*;

import java.util.List;

// 서버 -> 클라이언트
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyListResponseDTO {


    private int count; // 총 댓글 수
    private PageMaker pageInfo; // 페이지 정보
    //    private List<Reply> replies; // 댓글 리스트
    private List<ReplyDetailResponseDTO> replies; // 댓글 리스트

}
