package by.profsoft.work.facade.interfaces;

import by.profsoft.work.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface for category facade.
 */
public interface ICategoryFacade {
    /**
     * Save or update category.
     *
     * @param categoryDto the category dto object.
     * @return category dto object.
     */
    CategoryDto saveOrUpdate(CategoryDto categoryDto);

    /**
     * Get category by id.
     *
     * @param id the id category object.
     * @return dto category object.
     */
    CategoryDto getCategoryById(Long id);

    /**
     * Get all categories.
     *
     * @param pageable the pageable.
     * @return list of dto categories.
     */
    Page<CategoryDto> getAllCategory(Pageable pageable);

    /**
     * Delete category.
     *
     * @param categoryDto the dto category object.
     */
    void deleteCategory(CategoryDto categoryDto);

    /**
     * Get list of categories by own product.
     *
     * @param idProductDto the id productDto.
     * @param pageable     the pageable.
     * @return list of categories.
     */
    Page<CategoryDto> getCategoriesByProduct(Long idProductDto, Pageable pageable);

    /**
     * Find category by name.
     *
     * @param name the name searching category.
     * @return searched category.
     */
    CategoryDto getCategoryByName(String name);
}
