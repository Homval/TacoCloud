package ru.example.tacocloud.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Taco_Order")
@Schema(name = "Order", description = "Taco delivery order")
public class Order implements Serializable {

    @Schema(description = "SerialVersionUID")
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Schema(description = "Unique qualifier")
    private Long id;

    @Schema(description = "Creation date and time", accessMode = Schema.AccessMode.READ_ONLY)
    private Date placedAt;

    @NotBlank(message = "Name is required")
    @Schema(description = "Client name", example = "Mike Adams", required = true)
    private String name;

    @NotBlank(message = "Street is required")
    @Schema(description = "Delivery street name", example = "Apple street", required = true)
    private String street;

    @NotBlank(message = "City is required")
    @Schema(description = "Delivery city name", example = "Washington", required = true)
    private String city;

    @NotBlank(message = "State is required")
    @Schema(description = "Delivery state name", example = "Montana", required = true)
    private String state;

    @NotBlank(message = "Zip code is required")
    @Schema(description = "Delivery zip name", example = "135184", required = true)
    private String zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    @Schema(description = "Credit card number", example = "1234567812345678", required = true)
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    @Schema(description = "Credit card expiration date", example = "12/28", required = true)
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @Schema(description = "Credit card CVV", example = "135", required = true)
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    @Schema(description = "List of tacos")
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

    @PrePersist
    private void createdAt(){
        this.placedAt = new Date();
    }
}
