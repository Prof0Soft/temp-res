package by.profsoft.work.repository;

import by.profsoft.work.model.Category;
import by.profsoft.work.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for category.
 */
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Find category by product
     *
     * @param product  the product.
     * @param pageable the pageable.
     * @return list of products object.
     */
    Page<Category> findAllByProducts(Product product, Pageable pageable);

    /**
     * Find category by name.
     *
     * @param name the name searching category.
     * @return searched category.
     */
    Category findByNameCategory(String name);
}
