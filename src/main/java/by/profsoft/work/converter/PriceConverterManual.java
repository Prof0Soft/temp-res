package by.profsoft.work.converter;

import by.profsoft.work.converter.interfaces.IPriceConverter;
import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.model.Price;
import by.profsoft.work.service.intarfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Manual converter for price.
 */
@Component
public class PriceConverterManual implements IPriceConverter {
    private final IProductService productService;

    /**
     * Constructor with depend.
     *
     * @param productService the product service.
     */
    @Autowired
    public PriceConverterManual(final IProductService productService) {
        this.productService = productService;
    }

    @Override
    public PriceDto convertToDto(final Price price) {
        PriceDto priceDto = new PriceDto();

        priceDto.setId(price.getId());
        priceDto.setPrice(price.getPrice());
        priceDto.setCurrency(price.getCurrency());
        if (price.getProduct() != null) {
            priceDto.setIdProduct(price.getProduct().getId());
        } else {
            priceDto.setIdProduct(null);
        }
        return priceDto;
    }

    @Override
    public Price convertToEntity(final PriceDto priceDto) {
        Price price = new Price();

        price.setId(priceDto.getId());
        price.setPrice(priceDto.getPrice());
        price.setCurrency(priceDto.getCurrency());
        if (priceDto.getIdProduct() != null) {
            price.setProduct(productService.getProductById(priceDto.getIdProduct()));
        } else {
            price.setProduct(null);
        }
        return price;
    }
}
