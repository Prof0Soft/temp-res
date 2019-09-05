package by.profsoft.work;

import by.profsoft.work.model.Product;
import by.profsoft.work.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan
public class ProductServiceTest {
    private Pageable pageable = new QPageRequest(1, 2);
    @Autowired
    ProductService productService;

    @Test
    public void saveProduct() {
        String nameProduct = "test product";
        Product product = new Product();
        product.setNameProduct(nameProduct);

        productService.saveProduct(product);

        Long id = product.getId();

        product = productService.getProductById(id);
        assertThat(product.getNameProduct(), is(greaterThanOrEqualTo(nameProduct)));
    }

    @Test
    public void getAllProducts() {
        Page<Product> products = productService.getAllProducts(pageable);
        assertThat(products.getContent().size(), is(greaterThanOrEqualTo(0)));
    }

    @Test
    public void deleteProductById() {
        Product product = new Product();
        product.setNameProduct("test product");
        Long idProduct = productService.saveProduct(product).getId();

        productService.deleteProduct(product);
        product = productService.getProductById(idProduct);
        Assert.assertNull(product);
    }

    @Test
    public void getProductById() {
        Product product = productService.getProductById(1L);
        assertThat(product.getId(), is(greaterThanOrEqualTo(1L)));
    }
}