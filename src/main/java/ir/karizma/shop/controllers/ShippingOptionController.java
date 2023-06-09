package ir.karizma.shop.controllers;

import ir.karizma.shop.dto.ShippingOptionDTO;
import ir.karizma.shop.services.ShippingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping-options")
public class ShippingOptionController {
    private final ShippingService shippingService;

    public ShippingOptionController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping("")
    public List<ShippingOptionDTO> getAllShippingOptions() {
        return shippingService.getAllShippingOptions();
    }

    @GetMapping("/{id}")
    public ShippingOptionDTO getShippingOptionById(@PathVariable @Positive(message = "Shipping option ID must be a positive integer") Long id) {
        return shippingService.getShippingOptionById(id);
    }
}