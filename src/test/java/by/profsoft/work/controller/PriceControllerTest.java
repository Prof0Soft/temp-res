package by.profsoft.work.controller;

import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.facade.PriceFacade;
import by.profsoft.work.facade.ProductFacade;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static utils.AsJsonString.asJsonString;
import static utils.TestUtils.APPLICATION_JSON_UTF8;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceControllerTest {

    private final String baseUrl = "/price";

    @Autowired
    PriceFacade priceFacade;
    @Autowired
    ProductFacade productFacade;

    @Autowired
    private WebApplicationContext context;

   /* @Autowired
    private FilterChainProxy springSecurityFilterChain;*/

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
//                .addFilters(springSecurityFilterChain)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @WithMockUser
    @Test
    public void runAndStatusIsOk() throws Exception {
        this.mockMvc.perform(get(baseUrl + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void accessIsDenied() throws Exception {
        this.mockMvc.perform(get(baseUrl + "/1"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/all"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/product"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/save"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/delete"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get(baseUrl + "/between/10.0-100.0"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void createPrice() throws Exception {
        String url = baseUrl + "/create";
//        String accessToken = obtainAccessToken("admin", "nimda");

        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(111.111);
        priceDto.setCurrency("RUR");
        priceDto.setIdProduct(1L);

        String requestJson = asJsonString(priceDto);

        this.mockMvc.perform(get(url)
                .contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isCreated());

    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void updatePrice() throws Exception {
        String url = baseUrl + "/save";

        PriceDto priceDto = new PriceDto();
        priceDto.setId(1L);
        priceDto.setPrice(111.111);
        priceDto.setCurrency("RUR");
        priceDto.setIdProduct(2L);

        String requestJson = asJsonString(priceDto);

        this.mockMvc.perform(get(url)
                .contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void getAll() throws Exception {
        String url = baseUrl + "/all";

        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void getPriceById() throws Exception {
        String url = baseUrl + "/1";

        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void deletePrice() throws Exception {
        String url = baseUrl + "/delete";

        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(111.111);
        priceDto.setCurrency("RUR");
        priceDto = priceFacade.saveOrUpdate(priceDto);

        String requestJson = asJsonString(priceDto);

        this.mockMvc.perform(get(url)
                .contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isAccepted());

        PriceDto priceDtoNew = priceFacade.getPriceById(priceDto.getId());
        assertNull(priceDtoNew);
    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void getPriceCurrency() throws Exception {
        String url = baseUrl + "/currency/RUR";

        MvcResult mvcResult = this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(greaterThanOrEqualTo(0))))
                .andReturn();
    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void getPriceByProduct() throws Exception {
        String url = baseUrl + "/product";

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);


        String requestJson = asJsonString(productDto);

        this.mockMvc.perform(get(url)
                .contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(greaterThanOrEqualTo(0))));
    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void getPriceBetweenValues() throws Exception {
        Double valueOne = 100.0;
        Double valueTwo = 150.0;

        String url = baseUrl + "/between/" + valueOne + "-" + valueTwo;

        MvcResult mvcResult = this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(greaterThanOrEqualTo(0))))
                .andReturn();
    }
    /*private String obtainAccessToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "fooClientIdPassword");
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("fooClientIdPassword", "secret"))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }*/

}