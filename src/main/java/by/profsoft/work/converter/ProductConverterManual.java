package by.profsoft.work.converter;

import by.profsoft.work.converter.interfaces.IPriceConverter;
import by.profsoft.work.converter.interfaces.IProductConverter;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.model.Category;
import by.profsoft.work.model.Product;
import by.profsoft.work.service.CategoryService;
import by.profsoft.work.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Impl. manual converter for product.
 */
@Component
public class ProductConverterManual implements IProductConverter {
    private Pageable pageable = PageRequest.of(1, 1);
    private final ProductService productService;
    private final CategoryService categoryService;
    private final IPriceConverter iPriceConverter;

    /**
     * Constructor with depend.
     *
     * @param productService  the product service.
     * @param categoryService the category service.
     * @param iPriceConverter the price converter.
     */
    @Autowired
    public ProductConverterManual(final ProductService productService, final CategoryService categoryService,
                                  final IPriceConverter iPriceConverter) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.iPriceConverter = iPriceConverter;
    }

    @Override
    public ProductDto convertToDto(final Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setNameProduct(product.getNameProduct());
        if (product.getPrices() != null) {
            productDto.setPrices(product.getPrices().stream()
                    .map(iPriceConverter::convertToDto)
                    .collect(Collectors.toList()));
        } else {
            product.setPrices(null);
        }
        Page<Category> categories = categoryService.findAllByProducts(product, pageable);
        productDto.setIdCategories(categories.getContent()
                .stream()
                .map(Category::getId)
                .collect(Collectors.toList()));
        return productDto;
    }

    @Override
    public Product convertToEntity(final ProductDto productDto) {
        Product product = new Product();

        product.setId(productDto.getId());
        product.setNameProduct(productDto.getNameProduct());
        product.setPrices(null);
        product.setCategories(productDto.getIdCategories()
                .stream()
                .map(categoryService::getCategoryById)
                .collect(Collectors.toList()));
        return product;
    }
}
