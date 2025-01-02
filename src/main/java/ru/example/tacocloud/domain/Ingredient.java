package ru.example.tacocloud.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Schema(description = "Taco ingredient")
public class Ingredient {

    @Id
    @Schema(description = "unique qualifier")
    private final String id;

    @Schema(description = "Ingredient name", example = "Cheddar")
    private final String name;

    @Schema(description = "Ingredient type", example = "WRAP", allowableValues = {"WRAP", "PROTEIN", "VEGGIES", "CHEESE", "SAUCE"})
    private final Type type;


    public enum Type{
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
