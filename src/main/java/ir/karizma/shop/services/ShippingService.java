package ir.karizma.shop.services;

import ir.karizma.shop.domain.*;
import ir.karizma.shop.dto.ShippingMethodDTO;
import ir.karizma.shop.dto.ShippingOptionDTO;
import ir.karizma.shop.mappers.ShippingMapper;
import ir.karizma.shop.repositories.ShippingMethodRepository;
import ir.karizma.shop.repositories.ShippingOptionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingService {
    private final ShippingMethodRepository shippingMethodRepository;
    private final ShippingOptionRepository shippingOptionRepository;
    private final ShippingMapper shippingMapper;

    public ShippingService(ShippingMethodRepository shippingMethodRepository, ShippingOptionRepository shippingOptionRepository, ShippingMapper shippingMapper) {
        this.shippingMethodRepository = shippingMethodRepository;
        this.shippingOptionRepository = shippingOptionRepository;
        this.shippingMapper = shippingMapper;
    }

    public List<ShippingMethodDTO> getAllShippingMethods() {
        return shippingMethodRepository.findAll().stream().map(shippingMapper::toMethodDto).collect(Collectors.toList());
    }

    public ShippingOptionDTO getShippingMethodByName(String name) {
        return shippingMapper.toDto(shippingOptionRepository.findByName(name));
    }

    public List<ShippingOptionDTO> getAllShippingOptions() {
        return shippingOptionRepository.findAll().stream().map(shippingMapper::toDto).collect(Collectors.toList());
    }

    public ShippingOptionDTO getShippingOptionById(Long id) {
        ShippingOption shippingOption = shippingOptionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("shipping option not found"));
        return shippingMapper.toDto(shippingOption);
    }

    public ShippingOptionDTO getShippingOptionByMethodAndPrice(ShippingMethod method, BigDecimal price) {
        return shippingMapper.toDto(shippingOptionRepository.findByMethodAndPriceLessThanEqual(method, price));
    }

    public ShippingMethodDTO getShippingMethodById(Long id) {
        ShippingMethod shippingMethod = shippingMethodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shipping method not found"));
        return shippingMapper.toMethodDto(shippingMethod);
    }

}