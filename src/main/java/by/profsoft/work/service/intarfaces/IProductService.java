package by.profsoft.work.service.intarfaces;

import by.profsoft.work.model.Category;
import by.profsoft.work.model.Price;
import by.profsoft.work.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Interface for product service.
 */
public interface IProductService {
    /**
     * Add/update product to database.
     *
     * @param product the product.
     * @return product object.
     */
    Product saveProduct(Product product);

    /**
     * Find all products from database.
     *
     * @param pageable the pageable.
     * @return list of all products.
     */
    Page<Product> getAllProducts(Pageable pageable);

    /**
     * Delete product from base by id.
     *
     * @param product the product object.
     */
    void deleteProduct(Product product);

    /**
     * Find product by id.
     *
     * @param id the product id.
     * @return product object.
     */
    Product getProductById(Long id);

    /**
     * Find product by name.
     *
     * @param name the name product.
     * @return product object.
     */
    Product findByNameProduct(String name);

    /**
     * Find by price.
     *
     * @param price the price.
     * @return product object.
     */
    Product findByPrices(Price price);

    /**
     * Find product by category.
     *
     * @param category the category.
     * @param pageable the pageable.
     * @return searched category.
     */
    Page<Product> findByCategories(Category category, Pageable pageable);
}
