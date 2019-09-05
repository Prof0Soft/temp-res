package by.profsoft.work.converter;

import by.profsoft.work.converter.interfaces.ICategoryConverter;
import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.model.Category;
import by.profsoft.work.model.Product;
import by.profsoft.work.service.CategoryService;
import by.profsoft.work.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manual Category converter.
 */
@Component
public class CategoryConverterManual implements ICategoryConverter {
    private final CategoryService categoryService;
    private final ProductService productService;

    /**
     * Constructor with converter depend.
     *
     * @param categoryService the category service.
     * @param productService  the product service.
     */
    @Autowired
    public CategoryConverterManual(final CategoryService categoryService, final ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public CategoryDto convertToDto(final Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setNameCategory(category.getNameCategory());
        List<Long> productDtos = category.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        categoryDto.setIdProducts(productDtos);
        if (category.getParent() != null) {
            categoryDto.setIdParent(category.getParent().getId());
        } else {
            categoryDto.setIdParent(null);
        }
        if (category.getSubCategories() != null && category.getSubCategories().size() > 0) {
            categoryDto.setIdSubCategories(category.getSubCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toList()));
        } else {
            categoryDto.setIdSubCategories(null);
        }

        return categoryDto;
    }

    @Override
    public Category convertToEntity(final CategoryDto categoryDto) {
        Category category = new Category();

        category.setId(categoryDto.getId());
        category.setNameCategory(categoryDto.getNameCategory());

        category.setProducts(categoryDto.getIdProducts().stream()
                .map(productService::getProductById)
                .collect(Collectors.toList()));

        if (categoryDto.getIdParent() != null) {
            category.setParent(categoryService.getCategoryById(categoryDto.getIdParent()));
        } else {
            category.setParent(null);
        }
        if (categoryDto.getIdSubCategories() != null) {
            category.setSubCategories(categoryDto.getIdSubCategories().stream()
                    .map(categoryService::getCategoryById)
                    .collect(Collectors.toList()));
        } else {
            category.setSubCategories(null);
        }
        return category;
    }
}
