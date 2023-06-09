package ir.karizma.shop.mappers;

import ir.karizma.shop.domain.Order;
import ir.karizma.shop.domain.Product;
import ir.karizma.shop.dto.OrderDTO;
import ir.karizma.shop.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDto(Order product);
    Order toEntity(OrderDTO orderDTO);
}
