package ir.karizma.shop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDTO {
    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @Positive(message = "Quantity must be a positive integer")
    private int quantity;
}
