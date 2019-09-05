package by.profsoft.work.converter.interfaces;

import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.model.Product;

/**
 * Interface for product converter.
 */
public interface IProductConverter {
    /**
     * Convert entity object to dto.
     *
     * @param product the entity product object.
     * @return converted dto object.
     */
    ProductDto convertToDto(Product product);

    /**
     * Convert dto object to entity.
     *
     * @param productDto the dto product object.
     * @return product entity.
     */
    Product convertToEntity(ProductDto productDto);
}
