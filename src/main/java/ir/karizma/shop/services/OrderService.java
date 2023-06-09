package ir.karizma.shop.services;

import ir.karizma.shop.domain.*;
import ir.karizma.shop.repositories.DiscountRepository;
import ir.karizma.shop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final DiscountRepository discountRepository;
    private final ShippingService shippingService;

    public OrderService(OrderRepository orderRepository, ProductService productService, DiscountRepository discountRepository, ShippingService shippingService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.discountRepository = discountRepository;
        this.shippingService = shippingService;
    }

    public Order registerOrder(Customer customer, List<OrderItem> items, BigDecimal discountPercentage, BigDecimal discountAmount, String shippingMethodName) {
        // Validate order
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem item : items) {
            Product product = productService.getProduct(item.getProduct().getId());
            if (product == null) {
                throw new IllegalArgumentException("Product not found: " + item.getProduct().getId());
            }
            item.setProduct(product);
            item.setPrice(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            totalPrice = totalPrice.add(item.getPrice());
        }
        if (totalPrice.compareTo(BigDecimal.valueOf(50000)) < 0) {
            throw new IllegalArgumentException("Order total must be at least 50,000 Tomans");
        }
        Discount discount = null;
        if (discountPercentage != null) {
            discount = discountRepository.findByPercentage(discountPercentage);
        } else if (discountAmount != null) {
            discount = discountRepository.findByAmount(discountAmount);
        }
        if (discount != null) {
            totalPrice = totalPrice.subtract(discount.getAmount());
        }
        ShippingMethod shippingMethod = shippingService.getShippingMethodByName(shippingMethodName);
        if (shippingMethod == null) {
            throw new IllegalArgumentException("Shipping method not found: " + shippingMethodName);
        }
        ShippingOption shippingOption = shippingService.getShippingOptionByMethodAndPrice(shippingMethod, totalPrice);
        if (shippingOption == null) {
            throw new IllegalArgumentException("Shipping option not found for total price: " + totalPrice);
        }
        Order order = new Order();
        order.setCustomer(customer);
        order.setItems(items);
        order.setDiscountPercentage(discountPercentage);
        order.setDiscountAmount(discountAmount);
        order.calculateTotal();
        orderRepository.save(order);
        return order;
    }
}