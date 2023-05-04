package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
@Builder

// requestDTO (클라이언트 -> 서버) : 세터, 노아그 필수!!
// responseDTO (서버 -> 클라이언트)
public class ReplyPostRequestDTO {
    // RequestDTO는 클라이언트가 제대로 값을 보냈는지 검증해야함!
    // -> build.gradle에 라이브러리 추가하고 -> 필드에 @ 필수값들 붙이기

    // < Entity <-> DB > 엔터티는 데이터베이스 컬럼명과 꼭 동일하게 하기! + Mapper
    // < DTO <-> 클라이언트 > DTO의 필드명은 클라이언트 개발자와 상의해야 함 + Controller
    @NotBlank // 필수값
    private String text; // 댓글내용

    @NotBlank // 필수값
    @Size(min = 2, max = 8)
    private String author; // 댓글 작성자명

    /*
        @NotNull    -   null을 허용하지 않음
        @NotBlank   -   null + ""을 허용하지 않음
    * */

    //  @NotBlank // 필수값 -> bno은 String이 아닌데 어떻게 ""을 쓰냠?!
    @NotNull // 필수값
    private Long bno; // 원본 글 번호
    // long으로 (소문자) 적으면 기본값 0으로 지정됨. 그래서 Long으로 쓰기!


    // dto를 entity로 바꿔서 리턴하는 메서드
    public Reply toEntity() {
        return Reply.builder()
                .replyText(this.text)
                .replyWriter(this.author)
                .boardNo(this.bno)
                .build();
    }

}
