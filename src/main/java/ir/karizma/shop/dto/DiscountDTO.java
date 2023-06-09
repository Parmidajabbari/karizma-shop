package ir.karizma.shop.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class DiscountDTO {
    @NotNull(message = "Discount ID cannot be null")
    private Long id;

    @DecimalMax(value = "100.00", message = "Discount percentage cannot be greater than 100")
    private BigDecimal percentage;

    @DecimalMin(value = "0.00", message = "Discount amount cannot be negative")
    private BigDecimal amount;
}
