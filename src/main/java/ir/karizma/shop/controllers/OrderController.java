package ir.karizma.shop.controllers;

import ir.karizma.shop.dto.OrderDTO;
import ir.karizma.shop.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable @Positive(message = "Order ID must be a positive integer") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
    }
}