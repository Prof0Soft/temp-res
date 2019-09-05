package by.profsoft.work.converter.interfaces;

import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.model.Price;

/**
 * Interface for price convert.
 */
public interface IPriceConverter {
    /**
     * Convert entity object to dto.
     *
     * @param price the entity price object.
     * @return converted dto object.
     */
    PriceDto convertToDto(Price price);

    /**
     * Convert dto object to entity.
     *
     * @param priceDto the dto price object.
     * @return price entity.
     */
    Price convertToEntity(PriceDto priceDto);
}
