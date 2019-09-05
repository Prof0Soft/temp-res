package by.profsoft.work.service.intarfaces;

import by.profsoft.work.model.Category;
import by.profsoft.work.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface for category service.
 */
public interface ICategoryService {
    /**
     * Add/update new category to database.
     *
     * @param category the category.
     * @return category object.
     */
    Category saveCategory(Category category);

    /**
     * Find all categories from database.
     *
     * @param pageable the pageable.
     * @return list of all categories.
     */
    Page<Category> getAllCategories(Pageable pageable);

    /**
     * Get category by id.
     *
     * @param id the id category.
     * @return category object.
     */
    Category getCategoryById(Long id);

    /**
     * Delete category.
     *
     * @param category the category.
     */
    void deleteCategory(Category category);

    /**
     * Find all by own product.
     *
     * @param product  the product object.
     * @param pageable the pageable.
     * @return list of categories.
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
