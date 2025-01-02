package ru.example.tacocloud.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Schema(description = "Taco")
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Schema(description = "Unique qualifier")
    private Long id;

    @Schema(description = "Creation date and time", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    @Schema(description = "Taco name", example = "My favorite taco", required = true)
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min=1, message="You must choose at least 1 ingredient")
    @Schema(description = "List of ingredients")
    private List<Ingredient> ingredients;

    @PrePersist
    private void createdAt() {
        this.createdAt = new Date();
    }

}
