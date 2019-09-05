package by.profsoft.work;

import by.profsoft.work.model.Price;
import by.profsoft.work.service.PriceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan
public class PriceServiceTest {

    @Autowired
    PriceService priceService;

    @Test
    public void findAllPrices() {
//        List<Price> prices = priceService.getAllPrices();
//        assertThat(prices.size(), is(greaterThanOrEqualTo(0)));
    }

    @Test
    public void savePrice() {
        Double valuePrice = 111.111;
        Price price = new Price();
        price.setPrice(valuePrice);
        priceService.savePrice(price);
        Long id = price.getId();

        price = priceService.getPriceById(id);
        assertThat(price.getPrice(), is(greaterThanOrEqualTo(valuePrice)));
    }

    @Test
    public void findByid() {
        Price price = priceService.getPriceById(1L);
        assertThat(price.getId(), is(greaterThanOrEqualTo(1L)));
    }

    @Test
    public void deletePrice() {
        Price price = new Price();
        price.setPrice(111.111);
        price.setCurrency("RUR");
        Long idPrice = priceService.savePrice(price).getId();

        priceService.deletePrice(price);
        price = priceService.getPriceById(idPrice);
        Assert.assertNull(price);
    }
}