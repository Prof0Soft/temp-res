package by.profsoft.work.facade;

import by.profsoft.work.converter.interfaces.ICategoryConverter;
import by.profsoft.work.converter.interfaces.IProductConverter;
import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.facade.interfaces.ICategoryFacade;
import by.profsoft.work.model.Category;
import by.profsoft.work.service.intarfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Category facade.
 */
@Service
public class CategoryFacade implements ICategoryFacade {
    private final ICategoryService categoryService;
    private final ICategoryConverter converter;
    private final IProductConverter iProductConverter;

    /**
     * Constructor for category facade.
     *
     * @param categoryService the service for category.
     * @param converter       the converter for category.
     */
    @Autowired
    public CategoryFacade(final ICategoryService categoryService, final ICategoryConverter converter, IProductConverter iProductConverter) {
        this.categoryService = categoryService;
        this.converter = converter;
        this.iProductConverter = iProductConverter;
    }

    @Override
    public CategoryDto saveOrUpdate(final CategoryDto categoryDto) {
        Category category = categoryService.saveCategory(converter.convertToEntity(categoryDto));
        return converter.convertToDto(category);
    }

    @Override
    public CategoryDto getCategoryById(final Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return null;
        } else {
            return converter.convertToDto(category);
        }
    }

    @Override
    public Page<CategoryDto> getAllCategory(final Pageable pageable) {
        Page<Category> categories = categoryService.getAllCategories(pageable);
        List<CategoryDto> categoryDtos = categories.getContent()
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(categoryDtos, pageable, categories.getTotalElements());
    }

    @Override
    public void deleteCategory(final CategoryDto categoryDto) {
        categoryService.deleteCategory(converter.convertToEntity(categoryDto));
    }

    @Override
    public CategoryDto getCategoryByName(final String name) {
        return converter.convertToDto(categoryService.findByNameCategory(name));
    }

    @Override
    public Page<CategoryDto> getCategoriesByProduct(final Long idProductDto, final Pageable pageable) {
        ProductDto productDto = new ProductDto();
        productDto.setId(idProductDto);
        Page<Category> categories = categoryService.findAllByProducts(iProductConverter
                .convertToEntity(productDto), pageable);
        List<CategoryDto> categoryDtos = categories.getContent()
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(categoryDtos, pageable, categories.getTotalElements());
    }
}
