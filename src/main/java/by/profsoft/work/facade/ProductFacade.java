package by.profsoft.work.facade;

import by.profsoft.work.converter.interfaces.ICategoryConverter;
import by.profsoft.work.converter.interfaces.IPriceConverter;
import by.profsoft.work.converter.interfaces.IProductConverter;
import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.facade.interfaces.IProductFacade;
import by.profsoft.work.model.Product;
import by.profsoft.work.service.intarfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Product facade.
 */
@Service
public class ProductFacade implements IProductFacade {
    private final IProductService productService;
    private final IProductConverter converter;
    private final IPriceConverter priceConverter;
    private final ICategoryConverter categoryConverter;

    /**
     * Constructor for product service depend.
     *
     * @param productService    the product service depend.
     * @param converter         the model mapper for convert.
     * @param categoryConverter the category converter.
     * @param priceConverter    the price converter.
     */
    @Autowired
    public ProductFacade(final IProductService productService, final IProductConverter converter,
                         final IPriceConverter priceConverter, final ICategoryConverter categoryConverter) {
        this.productService = productService;
        this.converter = converter;
        this.priceConverter = priceConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public ProductDto saveOrUpdate(final ProductDto productDto) {
        Product product = productService.saveProduct(converter.convertToEntity(productDto));
        return converter.convertToDto(product);
    }

    @Override
    public ProductDto getProductById(final Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return null;
        } else {
            return converter.convertToDto(product);
        }
    }

    @Override
    public Page<ProductDto> getAllProduct(final Pageable pageable) {
        Page<Product> products = productService.getAllProducts(pageable);
        List<ProductDto> productDtos = products.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }

    @Override
    public void deleteProduct(final ProductDto productDto) {
        productService.deleteProduct(converter.convertToEntity(productDto));
    }

    @Override
    public ProductDto getByNameProduct(final String name) {
        return converter.convertToDto(productService.findByNameProduct(name));
    }

    @Override
    public ProductDto getByPrices(final PriceDto price) {
        return converter.convertToDto(productService.findByPrices(priceConverter.convertToEntity(price)));
    }

    @Override
    public Page<ProductDto> getByCategories(final CategoryDto categoryDto, final Pageable pageable) {
        Page<Product> products = productService
                .findByCategories(categoryConverter.convertToEntity(categoryDto), pageable);
        List<ProductDto> productDtos = products.getContent().stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }

    @Override
    public Page<ProductDto> getByCategories(final Long idCategory, final Pageable pageable) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(idCategory);
        return getByCategories(categoryDto, pageable);
    }
}
