package by.profsoft.work.converter;

import by.profsoft.work.converter.interfaces.ICategoryConverter;
import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.model.Category;
import org.modelmapper.ModelMapper;

/**
 * Impl. category convert.
 */

public class CategoryConverter implements ICategoryConverter {

    ModelMapper modelMapper;

    @Override
    public CategoryDto convertToDto(final Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public Category convertToEntity(final CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
