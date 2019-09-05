package by.profsoft.work.facade.interfaces;

import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface for product facade.
 */
public interface IProductFacade {
    /**
     * Save or update object.
     *
     * @param productDto the dto product object.
     * @return saved product dto object.
     */
    ProductDto saveOrUpdate(ProductDto productDto);

    /**
     * Get product by id
     *
     * @param id the id product.
     * @return dto product object.
     */
    ProductDto getProductById(Long id);

    /**
     * Get all product.
     * @param pageable the pageable.
     * @return list of dto product project.
     */
    Page<ProductDto> getAllProduct(Pageable pageable);

    /**
     * Delete product.
     *
     * @param productDto the dto object product.
     */
    void deleteProduct(ProductDto productDto);

    /**
     * Find product by name.
     *
     * @param name the name product.
     * @return product object.
     */
    ProductDto getByNameProduct(String name);

    /**
     * Find by price.
     *
     * @param price the price.
     * @return product object.
     */
    ProductDto getByPrices(PriceDto price);

    /**
     * Find product by category.
     *
     * @param categoryDto the category.
     * @param pageable    the pageable.
     * @return searched category.
     */
    Page<ProductDto> getByCategories(CategoryDto categoryDto, Pageable pageable);

    /**
     * Find product by category.
     *
     * @param idCategory the category.
     * @param pageable   the pageable.
     * @return searched category.
     */
    Page<ProductDto> getByCategories(Long idCategory, Pageable pageable);
}
