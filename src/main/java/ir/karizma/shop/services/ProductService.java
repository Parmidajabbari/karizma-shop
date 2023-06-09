package ir.karizma.shop.services;

import ir.karizma.shop.domain.*;
import ir.karizma.shop.dto.ProductDTO;
import ir.karizma.shop.mappers.ProductMapper;
import ir.karizma.shop.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    public ProductDTO getProduct(Long id) {
        return productMapper.toDto(getProductEntity(id));
    }

    public Product getProductEntity(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public ProductDTO saveProduct(ProductDTO product) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(product)));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}