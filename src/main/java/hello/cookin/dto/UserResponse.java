package hello.cookin.dto;

import hello.cookin.entity.User;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserResponse {
    private Long userId;
    private String username;
    private String name;
    private LocalDate birth; // ✅ 생일도 포함해야 함!

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.birth = user.getBirth(); // ✅ 누락되었을 수 있음
    }
}



