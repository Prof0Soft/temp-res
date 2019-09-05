package by.profsoft.work.converter;

import by.profsoft.work.converter.interfaces.IPriceConverter;
import by.profsoft.work.converter.modelMapper.ModelMapperInj;
import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.model.Price;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Impl. for price converter.
 */

public class PriceConverter implements IPriceConverter {

    private final ModelMapperInj modelMapper;

    /**
     * Constructor with depends.
     *
     * @param modelMapper the model mapper.
     */
    @Autowired
    public PriceConverter(final ModelMapperInj modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public PriceDto convertToDto(final Price price) {
        return modelMapper.map(price, PriceDto.class);
    }

    @Override
    public Price convertToEntity(final PriceDto priceDto) {
        return modelMapper.map(priceDto, Price.class);
    }
}
