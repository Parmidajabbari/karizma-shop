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
public class OrderDTO {
    @NotNull(message = "Order ID cannot be null")
    private Long id;

    @NotEmpty(message = "Order must have at least one item")
    private List<@Valid OrderItemDTO> orderItems;

    @DecimalMin(value = "0.01", message = "Total price must be greater than or equal to 0.01")
    private BigDecimal total;

    @DecimalMax(value = "100.00", message = "Discount percentage cannot be greater than 100")
    private BigDecimal discountPercentage;

    @DecimalMin(value = "0.00", message = "Discount amount cannot be negative")
    private BigDecimal discountAmount;

    @DecimalMin(value = "0.00", message = "Shipping price cannot be negative")
    private BigDecimal shippingPrice;
}
