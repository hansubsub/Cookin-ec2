package hello.cookin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class IngredientResponse {
    private Long id;
    private String name;
    private LocalDate expirationDate;

    public IngredientResponse(Long id, String name, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
    }
}