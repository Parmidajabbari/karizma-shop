package ir.karizma.shop.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ShippingOptionDTO {
    @NotNull(message = "Shipping option ID cannot be null")
    private Long id;

    @NotNull(message = "Shipping method cannot be null")
    @Valid
    private ShippingMethodDTO method;

    @DecimalMin(value = "0.00", message = "Shipping option price cannot be negative")
    private BigDecimal price;
}
