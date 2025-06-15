package hello.cookin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserJoinRequest {
    private String username;   // 아이디
    private String password;   // 비밀번호
    private String name;       // 이름
    private LocalDate birth;   // 생년월일
}
