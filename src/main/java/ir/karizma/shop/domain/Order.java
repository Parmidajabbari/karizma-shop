package ir.karizma.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    private BigDecimal total;
    @ManyToOne
    private Customer customer;

    private BigDecimal discountPercentage;

    private BigDecimal discountAmount;


    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public void calculateTotal() {
        total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getPrice());
        }
        total = total.subtract(discountAmount);
    }

    public void applyDiscount(Discount discount) {
        if (discountPercentage != null) {
            discountAmount = total.multiply(discountPercentage).divide(BigDecimal.valueOf(100));
        } else if (discountAmount != null) {
            discountPercentage = discountAmount.divide(total, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        }
        calculateTotal();
    }
}