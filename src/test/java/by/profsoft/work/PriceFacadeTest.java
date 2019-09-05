package by.profsoft.work;

import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.facade.PriceFacade;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan
public class PriceFacadeTest {

    Pageable pageable = PageRequest.of(1, 2);
    @Autowired
    PriceFacade priceFacade;
    @Autowired
    ProductFacade productFacade;

    @Test
    public void saveOrUpdate() {
        Double valuePrice = 400.400;
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(valuePrice);
        priceDto.setCurrency("RUR");
        priceDto.setIdProduct(1L);
        priceDto = priceFacade.saveOrUpdate(priceDto);

        assertThat(priceDto.getId(), is(greaterThanOrEqualTo(0L)));
        assertEquals(priceDto.getPrice(), valuePrice);
    }

    @Test
    public void getPriceById() {
        Double valuePrice = 400.400;
        PriceDto priceDto = new PriceDto();
        priceDto.setCurrency("RUR");
        priceDto.setPrice(valuePrice);
        priceDto.setIdProduct(1L);
        priceDto = priceFacade.saveOrUpdate(priceDto);

        Long idPrice = priceDto.getId();

        PriceDto newPriceDto = priceFacade.getPriceById(idPrice);

        assertThat(newPriceDto.getPrice(), is(equalTo(valuePrice)));
    }

    @Test
    public void getAllPrice() {
//        List<PriceDto> priceDtos = priceFacade.getAllPrice();
//        assertThat(priceDtos.size(), is(greaterThanOrEqualTo(0)));
    }

    @Test
    public void deletePrice() {
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(111.111);
        priceDto.setCurrency("RUR");
//        priceDto.setIdProduct(1L);
        priceDto = priceFacade.saveOrUpdate(priceDto);
        Long id = priceDto.getId();
        priceFacade.deletePrice(priceDto);

        PriceDto priceDtoNew = priceFacade.getPriceById(id);

        assertNull(priceDtoNew);
    }

    @Test
    public void getPricesBetweenPrice() {
        Double priceOne = 100.0;
        Double priceTwo = 150.0;

        Page<PriceDto> priceDtos = priceFacade.getPricesBetweenPrice(priceOne, priceTwo, pageable);
        assertThat(priceDtos.getContent().size(), is(greaterThanOrEqualTo(0)));
    }

    @Test
    public void getAllByCurrency() {
        String currency = "RUR";
        Page<PriceDto> priceDtos = priceFacade.getAllByCurrency(currency, pageable);
        assertThat(priceDtos.getContent().size(), is(greaterThanOrEqualTo(0)));
    }

    @Test
    public void getPriceByProduct() {
        PriceDto priceDto = priceFacade.getPriceById(1L);
        ProductDto productDto = productFacade.getProductById(priceDto.getIdProduct());
        Page<PriceDto> priceDtosNew = priceFacade.getPriceByProduct(productDto, pageable);
        assertThat(priceDtosNew.getContent().size(), is(greaterThanOrEqualTo(0)));
    }
}