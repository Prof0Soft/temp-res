package by.profsoft.work.controller;

import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.facade.CategoryFacade;
import by.profsoft.work.facade.PriceFacade;
import by.profsoft.work.facade.ProductFacade;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import utils.AsJsonString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

    private final String baseUrl = "/product";

    @Autowired
    PriceFacade priceFacade;
    @Autowired
    ProductFacade productFacade;
    @Autowired
    CategoryFacade categoryFacade;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @WithMockUser
    @Test
    public void connectItsOk() throws Exception {
        this.mockMvc.perform(get(baseUrl + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void accessIsDenied() throws Exception {
        this.mockMvc.perform(get(baseUrl + "/1"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/all"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/name"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/save"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/delete"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/category"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/price"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser
    @Test
    public void saveProduct() throws Exception {
        String url = baseUrl + "/save";

        ProductDto productDto = new ProductDto();
        productDto.setNameProduct("test product");
        productDto.setIdCategories(Collections.singletonList(1L));
        productDto.setPrices(Collections.singletonList(priceFacade.getPriceById(1L)));

        String requestJson = AsJsonString.asJsonString(productDto);

        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())));
    }

    @WithMockUser
    @Test
    public void getAllProduct() throws Exception {
        String url = baseUrl + "/all";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Matchers.greaterThanOrEqualTo(0))));
    }

    @WithMockUser
    @Test
    public void deleteProduct() throws Exception {
        String url = baseUrl + "/delete";

        ProductDto productDto = new ProductDto();
        productDto.setNameProduct("test product");
        productDto.setIdCategories(Collections.singletonList(1L));
        productDto.setPrices(Collections.singletonList(priceFacade.getPriceById(1L)));
        productDto = productFacade.saveOrUpdate(productDto);
        Long id1 = productDto.getId();

        String requestJson = AsJsonString.asJsonString(productDto);

        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isAccepted());

        ProductDto productDto1 = productFacade.getProductById(id1);
        assertNull(productDto1);
    }

    @WithMockUser
    @Test
    public void getProductById() throws Exception {
        String url = baseUrl + "/1";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @WithMockUser
    @Test
    public void getByNameProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setNameProduct("test product its wonderful");
        productDto.setIdCategories(Collections.singletonList(1L));
        productDto.setPrices(Collections.singletonList(priceFacade.getPriceById(1L)));
        productDto = productFacade.saveOrUpdate(productDto);

        String url = baseUrl + "/name/" + productDto.getNameProduct();

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameProduct", is(productDto.getNameProduct())));
    }

    @WithMockUser
    @Test
    public void getByPrices() throws Exception {
        String url = baseUrl + "/price";

        PriceDto priceById = priceFacade.getPriceById(1L);

        String requestJson = AsJsonString.asJsonString(priceById);

        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(greaterThanOrEqualTo(0))));
    }

    @WithMockUser
    @Test
    public void getByCategories() throws Exception {
        String url = baseUrl + "/category";

        CategoryDto categoryById = categoryFacade.getCategoryById(125L);

        String requestJson = AsJsonString.asJsonString(categoryById);

        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(greaterThanOrEqualTo(0))));
    }
}