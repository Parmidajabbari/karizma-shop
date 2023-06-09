package ir.karizma.shop.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ShippingMethodDTO {
    @NotNull(message = "Shipping method ID cannot be null")
    private Long id;

    @NotEmpty(message = "Shipping method name cannot be empty")
    private String name;

    @DecimalMin(value = "0.01", message = "Shipping method price must be greater than or equal to 0.01")
    private BigDecimal price;
}
