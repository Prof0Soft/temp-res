package by.profsoft.work.converter.interfaces;

import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.model.Category;

/**
 * Interface for product convert.
 */
public interface ICategoryConverter {
    /**
     * Convert entity object to dto.
     *
     * @param category the entity category object.
     * @return converted dto object.
     */
    CategoryDto convertToDto(Category category);

    /**
     * Convert dto object to entity.
     *
     * @param categoryDto the dto category object.
     * @return category entity.
     */
    Category convertToEntity(CategoryDto categoryDto);
}
