package com.spring.mvc.chap05.dto.response;

import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserResponseDTO {

    private String account;
    private String nickName;
    private String email;
    private String auth; // 멤버서비스에서 만들어짐
    private String profile;

}
