package hello.cookin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String username;   // 아이디
    private String password;
}
