package com.spring.mvc.chap05.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;

// 재활용 빈도가 높으면 내부클래스를 외부로 빼는게 나음
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDetailResponseDTO {

    private long rno;
    private String text;
    private String writer;
    private String account;
    // 원본글번호는 필요없다고 함

    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
    private LocalDateTime regDate;

    private String profile; // 여기서 profile라고 적으면 JSON의 키값이 이렇게 지정됨!

    // 엔터티를 DTO로 변환하는 생성자
    public ReplyDetailResponseDTO(Reply reply) {
        this.rno = reply.getReplyNo();
        this.text = reply.getReplyText();
        this.writer = reply.getReplyWriter();
        this.regDate = reply.getReplyDate();
        this.account = reply.getAccount();
        this.profile = reply.getProfileImage();
    }
}
