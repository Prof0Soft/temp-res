package by.profsoft.work;

import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.facade.CategoryFacade;
import by.profsoft.work.facade.ProductFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan
public class ProductFacadeTest {

    private Pageable pageable = PageRequest.of(1, 2);
    @Autowired
    ProductFacade productFacade;
    @Autowired
    CategoryFacade categoryFacade;

    @Test
    public void saveOrUpdate() {
        String name = "test product";
        ProductDto productDto = new ProductDto();
        productDto.setNameProduct(name);
        productDto = productFacade.saveOrUpdate(productDto);

        assertThat(productDto.getId(), is(greaterThanOrEqualTo(0L)));
        assertEquals(productDto.getNameProduct(), name);
    }

    @Test
    public void getProductById() {
        String name = "test product";
        ProductDto productDto = new ProductDto();
        productDto.setNameProduct(name);
        List<Long> idCategoryDto = new ArrayList<>();
        idCategoryDto.add(2L);
        productDto.setIdCategories(idCategoryDto);
        productDto = productFacade.saveOrUpdate(productDto);

        Long idProduct = productDto.getId();


        ProductDto newProductDto = productFacade.getProductById(idProduct);

        assertThat(newProductDto.getNameProduct(), is(equalTo(name)));
    }

    @Test
    public void getAllProduct() {
        Page<ProductDto> productDtos = productFacade.getAllProduct(pageable);
        assertThat(productDtos.getContent().size(), is(greaterThanOrEqualTo(0)));
    }

    @Test
    public void deleteProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setNameProduct("test product");
        productDto = productFacade.saveOrUpdate(productDto);
        Long id = productDto.getId();
        productFacade.deleteProduct(productDto);

        ProductDto productDtoNew = productFacade.getProductById(id);

        assertNull(productDtoNew);
    }

    @Test
    public void getByNameProduct() {
        ProductDto productDto = productFacade.getProductById(3L);
        ProductDto productDtoNew = productFacade.getByNameProduct(productDto.getNameProduct());
        assertEquals(productDto.getId(), productDtoNew.getId());
    }

    @Test
    public void getByPrices() {
        ProductDto productDto = productFacade.getProductById(3L);
        PriceDto priceDto;
        if (productDto.getPrices().size() > 0) {
            priceDto = productDto.getPrices().get(0);
        } else {
            priceDto = null;
        }
        ProductDto productDtoNew = productFacade.getByPrices(priceDto);
        assertEquals(productDto.getId(), productDtoNew.getId());
    }

    @Test
    public void getByCategories() {
        ProductDto productDto = productFacade.getProductById(3L);
        CategoryDto categoryDto;
        if (productDto.getIdCategories().size() > 0) {
            categoryDto = categoryFacade.getCategoryById(productDto.getIdCategories().get(0));
        } else {
            categoryDto = null;
        }
        Page<ProductDto> byCategories = productFacade.getByCategories(categoryDto, pageable);
        assertThat(byCategories.getContent().size(), is(greaterThanOrEqualTo(1)));
    }

    @Test
    public void getByCategoriesWithIdCategory() {
        ProductDto productDto = productFacade.getProductById(3L);
        Long idCategoryDto;
        if (productDto.getIdCategories().size() > 0) {
            idCategoryDto = productDto.getIdCategories().get(0);
        } else {
            idCategoryDto = null;
        }
        Page<ProductDto> byCategories = productFacade.getByCategories(idCategoryDto, pageable);
        assertThat(byCategories.getContent().size(), is(greaterThanOrEqualTo(1)));
    }
}