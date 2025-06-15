package hello.cookin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class IngredientRequest {
    private String name;
    private LocalDate expirationDate;
    private Long userId;
}
