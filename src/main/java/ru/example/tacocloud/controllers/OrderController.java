package ru.example.tacocloud.controllers;


import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.example.tacocloud.data.OrderRepository;
import ru.example.tacocloud.domain.Order;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@Tag(name = "Order controller",
        description = "Order managing")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    @Operation(summary = "Order form", description = "Show order form")
    @SecurityRequirement(name = "sessionId")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    @Operation(summary = "Create order", description = "Process order and save order in DB")
    @SecurityRequirement(name = "sessionId")
    public String processOrder(@Valid Order order, Errors errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepository.save(order);
        status.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
