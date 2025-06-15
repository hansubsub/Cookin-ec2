package hello.cookin.controller;

import hello.cookin.dto.LoginRequest;
import hello.cookin.dto.UserJoinRequest;
import hello.cookin.dto.UserResponse;
import hello.cookin.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserJoinRequest joinRequest) {
        return userService.registerUser(joinRequest);
    }


    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        UserResponse userResponse = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        session.setAttribute("user", userResponse); // 세션에 저장
        return userResponse;
    }
    @GetMapping("/me")
    public UserResponse getCurrentUser(HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        if (user == null) {
            throw new IllegalStateException("로그인 필요");
        }
        return user;
    }
    @GetMapping("/profile")
    public UserResponse getUserProfile(HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        if (user == null) {
            throw new IllegalStateException("로그인 필요");
        }
        return user;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 종료
        return "로그아웃 완료";
    }

}