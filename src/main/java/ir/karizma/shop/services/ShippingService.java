package ir.karizma.shop.services;

import ir.karizma.shop.domain.*;
import ir.karizma.shop.repositories.ShippingMethodRepository;
import ir.karizma.shop.repositories.ShippingOptionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShippingService {
    private final ShippingMethodRepository shippingMethodRepository;
    private final ShippingOptionRepository shippingOptionRepository;

    public ShippingService(ShippingMethodRepository shippingMethodRepository, ShippingOptionRepository shippingOptionRepository) {
        this.shippingMethodRepository = shippingMethodRepository;
        this.shippingOptionRepository = shippingOptionRepository;
    }

    public List<ShippingMethod> getAllShippingMethods() {
        return shippingMethodRepository.findAll();
    }

    public ShippingMethod getShippingMethodByName(String name) {
        return shippingMethodRepository.findByName(name);
    }

    public List<ShippingOption> getAllShippingOptions() {
        return shippingOptionRepository.findAll();
    }

    public ShippingOption getShippingOptionByMethodAndPrice(ShippingMethod method, BigDecimal price) {
        return shippingOptionRepository.findByMethodAndPriceLessThanEqual(method, price);
    }
}