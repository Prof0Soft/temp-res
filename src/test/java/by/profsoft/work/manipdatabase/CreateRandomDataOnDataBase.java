package by.profsoft.work.manipdatabase;

import by.epamtraining.task_spring.model.*;
import by.profsoft.work.repository.IRoleRepository;
import by.profsoft.work.repository.IUserRepository;
import by.profsoft.work.service.CategoryService;
import by.profsoft.work.service.PriceService;
import by.profsoft.work.service.ProductService;
import by.profsoft.work.model.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Create random data.
 */
@Service
public class CreateRandomDataOnDataBase {

    private PriceService priceService;
    private ProductService productService;
    private CategoryService categoryService;
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;

    /**
     * Default constructor.
     */
    public CreateRandomDataOnDataBase() {
    }

    /**
     * Constructor with service decencies.
     *
     * @param priceService    the prices service.
     * @param productService  the product service.
     * @param categoryService the category service.
     */
    public CreateRandomDataOnDataBase(final IRoleRepository roleRepository, final IUserRepository userRepository,
                                      final PriceService priceService, final ProductService productService,
                                      final CategoryService categoryService) {
        this.priceService = priceService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create random count data.
     *
     * @param i the
     */
    public void createData(final int i) {
        RandomString randomString = new RandomString();
        Random random = new Random();

        // category
        for (int j = 0; j < i; j++) {
            Category category = new Category();
            category.setNameCategory("Cat" + j);
            categoryService.saveCategory(category);
            // sub category
            for (int k = 0; k < random.nextInt(i); k++) {
                Category subCategory = new Category();
                subCategory.setNameCategory("C" + j + "_" + k);
                subCategory.setParent(category);
                categoryService.saveCategory(subCategory);

                category.addSubCategory(subCategory);
            }
            categoryService.saveCategory(category);
        }

        Page<Category> categories = categoryService.getAllCategories(PageRequest.of(1, 100));

        // add product with prices
        for (int j = 0; j < i; j++) {
            Product product = new Product();
            product.setNameProduct("Product " + randomString.nextString());
            productService.saveProduct(product);

            List<String> currency = Arrays.asList("USD", "BYN", "RUR");

            int m = 0;
            // add prices
            for (int k = 0; k < random.nextInt(5) + 1; k++) {
                Price price = new Price();
                m++;
                m = m < 3 ? m : 0;
                price.setCurrency(currency.get(m));
                price.setPrice((random.nextDouble() + 1) * 100);
                price.setProduct(product);
                product.addPriceToProduct(price);
            }
            productService.saveProduct(product);

            Category category = categories.getContent().get(random.nextInt(i - 1) + 1);
            category.addProduct(product);
            categoryService.saveCategory(category);
        }

        // create some users with role
        Role role = new Role();
        role.setNameRole("USER");
        roleRepository.save(role);

        role = new Role();
        role.setNameRole("ADMIN");
        roleRepository.save(role);

        Role roleAdmin = new Role();
        roleAdmin = roleRepository.findByNameRole("ADMIN");

        Role roleUser = new Role();
        roleUser = roleRepository.findByNameRole("USER");


        User user = new User();
        user.setSocialId("Petty");
        user.setName("Petty");
        user.setPassword("1");
        user.setRole(roleAdmin);
        userRepository.save(user);

        user = new User();
        user.setName("Goga");
        user.setSocialId("Goga");
        user.setPassword("1");
        user.setRole(roleUser);
        userRepository.save(user);
    }
}
