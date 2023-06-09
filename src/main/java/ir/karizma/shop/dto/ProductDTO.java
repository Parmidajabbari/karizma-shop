package ir.karizma.shop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDTO {
    @NotNull(message = "Product ID cannot be null")
    private Long id;

    @NotEmpty(message = "Product name cannot be empty")
    private String name;

    @NotEmpty(message = "Product description cannot be empty")
    private String description;

    @DecimalMin(value = "0.01", message = "Product price must be greater than or equal to 0.01")
    private BigDecimal price;
}
