package hello.cookin.controller;

import hello.cookin.dto.IngredientRequest;
import hello.cookin.dto.IngredientResponse;
import hello.cookin.dto.UserResponse;
import hello.cookin.service.IngredientService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    // 세션 기반 식재료 추가
    @PostMapping("/add")
    public IngredientResponse addIngredient(@RequestBody IngredientRequest request, HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        if (user == null) throw new IllegalStateException("로그인 필요");

        request.setUserId(user.getUserId());
        return ingredientService.addIngredient(request);
    }

    // 세션 기반 식재료 조회
    @GetMapping("/user")
    public List<IngredientResponse> getIngredients(HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        if (user == null) throw new IllegalStateException("로그인 필요");

        return ingredientService.getIngredientsByUserId(user.getUserId());
    }

    @PutMapping("/update/{ingredientId}")
    public IngredientResponse updateIngredient(
            @PathVariable Long ingredientId,
            @RequestBody IngredientRequest request,
            HttpSession session) {

        UserResponse user = (UserResponse) session.getAttribute("user");
        if (user == null) throw new IllegalStateException("로그인 필요");

        // Optional: ingredientId의 소유자가 현재 user인지 검증할 수도 있음
        return ingredientService.updateIngredient(ingredientId, request.getName(), request.getExpirationDate());
    }


    @DeleteMapping("/delete/{ingredientId}")
    public String deleteIngredient(@PathVariable Long ingredientId, HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        if (user == null) throw new IllegalStateException("로그인 필요");

        // Optional: 해당 재료가 현재 유저의 것인지 확인하려면 서비스 단에서 검증 로직 추가
        ingredientService.deleteIngredient(ingredientId);
        return "삭제 완료";
    }
    @GetMapping("/expiring")
    public List<IngredientResponse> getExpiringIngredients(HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        if (user == null) throw new IllegalStateException("로그인 필요");

        return ingredientService.getExpiringIngredients(user.getUserId());
    }


}

