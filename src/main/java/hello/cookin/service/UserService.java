package hello.cookin.service;

import hello.cookin.dto.UserJoinRequest;
import hello.cookin.dto.UserResponse;
import hello.cookin.entity.User;
import hello.cookin.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse registerUser(UserJoinRequest joinRequest) {
        if (userRepository.findByUsername(joinRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User user = User.builder()
                .username(joinRequest.getUsername())
                .password(joinRequest.getPassword())
                .name(joinRequest.getName())
                .birth(joinRequest.getBirth())
                .build();
        userRepository.save(user);
        return new UserResponse(user);
    }


    public UserResponse loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        return new UserResponse(user);
    }
}
