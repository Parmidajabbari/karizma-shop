package ir.karizma.shop.mappers;

import ir.karizma.shop.domain.Product;
import ir.karizma.shop.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
}
