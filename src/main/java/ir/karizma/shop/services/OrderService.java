package ir.karizma.shop.services;

import ir.karizma.shop.domain.*;
import ir.karizma.shop.dto.OrderDTO;
import ir.karizma.shop.dto.OrderItemDTO;
import ir.karizma.shop.dto.ProductDTO;
import ir.karizma.shop.mappers.OrderMapper;
import ir.karizma.shop.repositories.DiscountRepository;
import ir.karizma.shop.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final DiscountRepository discountRepository;
    private final ShippingService shippingService;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ProductService productService, DiscountRepository discountRepository, ShippingService shippingService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.discountRepository = discountRepository;
        this.shippingService = shippingService;
        this.orderMapper = orderMapper;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("order not found"));
        return orderMapper.toDto(order);
    }


    public OrderDTO createOrder(OrderDTO orderDTO) {
        // Validate order
        if (orderDTO.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        Order order = new Order();
        List<Product> products = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {
            Product product = productService.getProductEntity(orderItemDTO.getProductId());
            if (product != null) {
                products.add(product);
                order.setTotal(order.getTotal().add(product.getPrice()));
            }
        }
        order.setDiscountAmount(orderDTO.getDiscountAmount());
        order.setDiscountPercentage(orderDTO.getDiscountPercentage());
        if (order.getTotal().compareTo(new BigDecimal(50000)) < 0) {
            throw new IllegalArgumentException("Order total price should be at least 50000");
        }
        return orderMapper.toDto(orderRepository.save(order));
    }
}