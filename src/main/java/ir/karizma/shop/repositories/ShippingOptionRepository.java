package ir.karizma.shop.repositories;

import ir.karizma.shop.domain.ShippingMethod;
import ir.karizma.shop.domain.ShippingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
@Transactional
public interface ShippingOptionRepository extends JpaRepository<ShippingOption, Long> {

    ShippingOption findByName(String name);

    ShippingOption findByMethodAndPriceLessThanEqual(ShippingMethod method, BigDecimal price);

}
