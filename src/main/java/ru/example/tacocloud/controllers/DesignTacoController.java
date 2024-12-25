package ru.example.tacocloud.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.example.tacocloud.data.IngredientRepository;
import ru.example.tacocloud.data.TacoRepository;
import ru.example.tacocloud.domain.Ingredient;
import ru.example.tacocloud.domain.Ingredient.Type;
import ru.example.tacocloud.domain.Order;
import ru.example.tacocloud.domain.Taco;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        log.info("Showing design form");
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors,
                                @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saved = tacoRepo.save(design);
        order.addDesign(saved); //TODO

        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        List<Ingredient> filteredIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType() == type) {
                filteredIngredients.add(ingredient);
            }
        }
        return filteredIngredients;
    }
}
