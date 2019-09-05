package by.profsoft.work;

import by.profsoft.work.manipdatabase.CreateRandomDataOnDataBase;
import by.profsoft.work.repository.IRoleRepository;
import by.profsoft.work.repository.IUserRepository;
import by.profsoft.work.service.CategoryService;
import by.profsoft.work.service.PriceService;
import by.profsoft.work.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan
public class TaskSpringApplicationTests {

    @Autowired
    PriceService priceService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    IUserRepository userRepository;

    @Test
    public void contextLoads() {

    }

    @Test
    public void createDataForBase() {
        CreateRandomDataOnDataBase createRandomDataOnDataBase = new CreateRandomDataOnDataBase(roleRepository,
                userRepository, priceService, productService, categoryService);
        createRandomDataOnDataBase.createData(100);
    }


}
