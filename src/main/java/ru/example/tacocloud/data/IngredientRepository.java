package ru.example.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.example.tacocloud.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
