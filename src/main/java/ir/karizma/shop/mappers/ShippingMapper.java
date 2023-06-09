package ir.karizma.shop.mappers;

import ir.karizma.shop.domain.Order;
import ir.karizma.shop.domain.ShippingMethod;
import ir.karizma.shop.domain.ShippingOption;
import ir.karizma.shop.dto.OrderDTO;
import ir.karizma.shop.dto.ShippingMethodDTO;
import ir.karizma.shop.dto.ShippingOptionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShippingMapper {
    ShippingOptionDTO toDto(ShippingOption shippingOption);
    ShippingMethodDTO toMethodDto(ShippingMethod shippingMethod);
    ShippingOption toEntity(ShippingOptionDTO shippingOptionDTO);
}
