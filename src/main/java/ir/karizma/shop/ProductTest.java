package ir.karizma.shop;

import ir.karizma.shop.domain.Product;
import ir.karizma.shop.repositories.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Product 1");
        product.setDescription("Description 1");
        product.setPrice(BigDecimal.valueOf(100));
        entityManager.persist(product);
        entityManager.flush();
        List<Product> products = productRepository.findAll();
        assert (products).size()==1;
        assert (products.get(0).getName()).equals("Product 1");
    }
}

