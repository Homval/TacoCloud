package ru.example.tacocloud.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.example.tacocloud.data.IngredientRepository;
import ru.example.tacocloud.domain.Ingredient;
import ru.example.tacocloud.domain.Ingredient.Type;
import ru.example.tacocloud.domain.Order;
import ru.example.tacocloud.domain.Taco;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Tag(name = "Design Taco Controller",
        description = "Create taco")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @GetMapping
    @Operation(summary = "Design taco form", description = "Show design form")
    @SecurityRequirement(name = "sessionId")
    public String showDesignForm() {
        log.info("Showing design form");
        return "design";
    }

    @PostMapping
    @Operation(summary = "Taco design", description = "Process taco design and add taco in order")
    @SecurityRequirement(name = "sessionId")
    public String processDesign(@Valid Taco taco, Errors errors,
                                @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        order.addTaco(taco);

        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }
}
