package hello.cookin.service;

import hello.cookin.dto.IngredientRequest;
import hello.cookin.dto.IngredientResponse;
import hello.cookin.entity.Ingredient;
import hello.cookin.entity.User;
import hello.cookin.repository.IngredientRepository;
import hello.cookin.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    @Transactional
    public IngredientResponse addIngredient(IngredientRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Ingredient ingredient = Ingredient.builder()
                .name(request.getName())
                .expirationDate(request.getExpirationDate())
                .user(user)
                .build();

        Ingredient saved = ingredientRepository.save(ingredient);
        return new IngredientResponse(saved.getId(), saved.getName(), saved.getExpirationDate());
    }

    public List<IngredientResponse> getIngredientsByUserId(Long userId) {
        List<Ingredient> ingredients = ingredientRepository.findByUserUserId(userId);
        return ingredients.stream()
                .map(i -> new IngredientResponse(i.getId(), i.getName(), i.getExpirationDate()))
                .collect(Collectors.toList());
    }

    @Transactional
    public IngredientResponse updateIngredient(Long ingredientId, String newName, LocalDate newExpirationDate) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));

        ingredient.setName(newName);
        ingredient.setExpirationDate(newExpirationDate);

        return new IngredientResponse(ingredient.getId(), ingredient.getName(), ingredient.getExpirationDate());
    }

    @Transactional
    public void deleteIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));

        ingredientRepository.delete(ingredient);
    }

    // ✅ 여기 추가
    public List<IngredientResponse> getExpiringIngredients(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        List<Ingredient> ingredients = ingredientRepository.findByUserUserId(userId);

        return ingredients.stream()
                .filter(i -> {
                    LocalDate exp = i.getExpirationDate();
                    return !exp.isBefore(today) && !exp.isAfter(tomorrow);
                })
                .map(i -> new IngredientResponse(i.getId(), i.getName(), i.getExpirationDate()))
                .collect(Collectors.toList());
    }
}

