package by.profsoft.work.converter;

import by.profsoft.work.converter.interfaces.IProductConverter;
import by.profsoft.work.converter.modelMapper.ModelMapperInj;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Impl. converter for products object.
 */
public class ProductConverter implements IProductConverter {
    final
    ModelMapperInj modelMapper;

    /**
     * Constructor with depend model mapper.
     *
     * @param modelMapper the model mapper.
     */
    @Autowired
    public ProductConverter(final ModelMapperInj modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto convertToDto(final Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public Product convertToEntity(final ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
