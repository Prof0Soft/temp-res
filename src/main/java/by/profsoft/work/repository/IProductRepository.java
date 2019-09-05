package by.profsoft.work.repository;

import by.profsoft.work.model.Category;
import by.profsoft.work.model.Price;
import by.profsoft.work.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for product.
 */
public interface IProductRepository extends JpaRepository<Product, Long> {
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
