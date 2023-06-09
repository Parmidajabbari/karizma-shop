package ir.karizma.shop;


import ir.karizma.shop.domain.Discount;
import ir.karizma.shop.domain.Order;
import ir.karizma.shop.domain.OrderItem;
import ir.karizma.shop.domain.Product;
import ir.karizma.shop.dto.OrderDTO;
import ir.karizma.shop.repositories.DiscountRepository;
import ir.karizma.shop.repositories.OrderRepository;
import ir.karizma.shop.services.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {
    @Mock
    private DiscountRepository discountRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;
    @Test
    public void testApplyDiscountPercentage() {
        Discount discount = new Discount();
        discount.setPercentage(BigDecimal.valueOf(10));
        when(discountRepository.findByPercentage(BigDecimal.valueOf(10))).thenReturn(discount);
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(100));
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(2);
        Order order = new Order();
        order.addItem(item);
        order.applyDiscount(new Discount(2L, BigDecimal.valueOf(10), null));
        assert order.getTotal().equals(BigDecimal.valueOf(180));
    }

    @Test
    public void testApplyDiscountAmount() {
        Discount discount = new Discount();
        discount.setAmount(BigDecimal.valueOf(50));
        when(discountRepository.findByAmount(BigDecimal.valueOf(50))).thenReturn(discount);
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(100));
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(2);
        Order order = new Order();
        order.addItem(item);
        order.applyDiscount(new Discount(1L, null, BigDecimal.valueOf(50)));
        assert(order.getTotal().equals(BigDecimal.valueOf(150)));
    }
    @Test
    public void testGetAllOrders() {
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(200));
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderRepository.findAll()).thenReturn(orders);
        List<OrderDTO> orderDTOs = orderService.getAllOrders();
        assert (orderDTOs.size() == 1);
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        order.setTotal(BigDecimal.valueOf(200));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        OrderDTO orderDTO = orderService.getOrderById(1L);
        assert (orderDTO.getId()).equals(1L);
        assert (orderDTO.getTotal()).equals(BigDecimal.valueOf(200));
    }
}