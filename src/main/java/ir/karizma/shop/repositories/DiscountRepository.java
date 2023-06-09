package ir.karizma.shop.repositories;

import ir.karizma.shop.domain.Discount;
import ir.karizma.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
@Transactional
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findByPercentage(BigDecimal amount);

    Discount findByAmount(BigDecimal percentage);

}
