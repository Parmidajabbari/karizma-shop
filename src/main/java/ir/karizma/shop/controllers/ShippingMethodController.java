package ir.karizma.shop.controllers;

import ir.karizma.shop.dto.ShippingMethodDTO;
import ir.karizma.shop.services.ShippingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shipping-methods")
public class ShippingMethodController {
    private final ShippingService shippingService;

    public ShippingMethodController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping("")
    public List<ShippingMethodDTO> getAllShippingMethods() {
        return shippingService.getAllShippingMethods();
    }

    @GetMapping("/{id}")
    public ShippingMethodDTO getShippingMethodById(@PathVariable @Positive(message = "Shipping method ID must be a positive integer") Long id) {
        return shippingService.getShippingMethodById(id);
    }
}