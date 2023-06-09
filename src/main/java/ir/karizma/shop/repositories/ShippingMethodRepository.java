package ir.karizma.shop.repositories;

import ir.karizma.shop.domain.Order;
import ir.karizma.shop.domain.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, Long> {
    ShippingMethod findByName(String name);
}
