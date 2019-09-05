package by.profsoft.work.facade;

import by.profsoft.work.converter.interfaces.IPriceConverter;
import by.profsoft.work.converter.interfaces.IProductConverter;
import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.facade.interfaces.IPriceFacade;
import by.profsoft.work.model.Price;
import by.profsoft.work.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Price facade.
 */
@Service
public class PriceFacade implements IPriceFacade {
    private final PriceService priceService;
    private final IPriceConverter converter;
    private final IProductConverter productConverter;

    /**
     * Constructor with depends.
     *
     * @param converter        the price converter interface.
     * @param priceService     the price service.
     * @param productConverter the product converter.
     */
    @Autowired
    public PriceFacade(final IPriceConverter converter, final PriceService priceService,
                       final IProductConverter productConverter) {
        this.converter = converter;
        this.priceService = priceService;
        this.productConverter = productConverter;
    }

    @Override
    public PriceDto saveOrUpdate(final PriceDto priceDto) {
        Price price = priceService.savePrice(converter.convertToEntity(priceDto));
        return converter.convertToDto(price);
    }

    @Override
    public PriceDto getPriceById(final Long id) {
        Price price = priceService.getPriceById(id);
        if (price == null) {
            return null;
        } else {
            return converter.convertToDto(price);
        }
    }

    @Override
    public Page<PriceDto> getAllPrice(final Pageable pageable) {
        Page<Price> prices = priceService.getAllPrices(pageable);

        List<PriceDto> priceDtos = prices.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(priceDtos, pageable, prices.getTotalElements());
    }

    @Override
    public void deletePrice(final PriceDto priceDto) {
        priceService.deletePrice(converter.convertToEntity(priceDto));
    }

    @Override
    public Page<PriceDto> getPricesBetweenPrice(final Double priceOne, final Double priceTwo, final Pageable pageable) {
        Page<Price> prices = priceService.findAllByPriceGreaterThanAndPriceLessThan(priceOne, priceTwo, pageable);

        List<PriceDto> priceDtos = prices.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(priceDtos, pageable, prices.getTotalElements());
    }

    @Override
    public Page<PriceDto> getAllByCurrency(final String currency, final Pageable pageable) {
        Page<Price> prices = priceService.findAllByCurrency(currency, pageable);
        List<PriceDto> priceDtos = prices.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(priceDtos, pageable, prices.getTotalElements());
    }

    @Override
    public Page<PriceDto> getPriceByProduct(final ProductDto productDto, final Pageable pageable) {
        Page<Price> prices = priceService.findByProduct(productConverter.convertToEntity(productDto), pageable);
        List<PriceDto> priceDtos = prices.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(priceDtos, pageable, prices.getTotalElements());
    }
}
