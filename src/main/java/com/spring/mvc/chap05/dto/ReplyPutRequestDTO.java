package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 클라이언트 -> 서버 수정하고싶다고 요청
// 게시글 번호랑 댓글 번호필요
public class ReplyPutRequestDTO {
    @Min(0) @Max(Long.MAX_VALUE) // 반드시 정수 입력하도록 체크
    private Long bno;
    @NotNull
    @Min(0) @Max(Long.MAX_VALUE)
    private Long rno;
    @NotBlank
    private String text; // 게시글

    public Reply toEntity() {
        return Reply.builder()
                .replyNo(this.rno)
                .boardNo(this.bno)
                .replyText(this.text)
                .build();
    }


}
