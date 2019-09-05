package by.profsoft.work.facade.interfaces;

import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface for price facade.
 */
public interface IPriceFacade {
    /**
     * Save or update price.
     *
     * @param priceDto the dto object of price.
     * @return price dto object.
     */
    PriceDto saveOrUpdate(PriceDto priceDto);

    /**
     * Get price by id.
     *
     * @param id the price id.
     * @return Dto price object.
     */
    PriceDto getPriceById(Long id);

    /**
     * Get all prices.
     *
     * @param pageable the pageble page.
     * @return List of dto prices object.
     */
    Page<PriceDto> getAllPrice(Pageable pageable);

    /**
     * Delete price.
     *
     * @param priceDto the dto object of price.
     */
    void deletePrice(PriceDto priceDto);

    /**
     * Find price between two prices.
     *
     * @param priceOne the first price.
     * @param priceTwo the second price.
     * @param pageable the pageable.
     * @return list of searched prices.
     */
    Page<PriceDto> getPricesBetweenPrice(Double priceOne, Double priceTwo, Pageable pageable);

    /**
     * Find all prices by currency.
     *
     * @param currency the seaching currency.
     * @param pageable the pageable.
     * @return searched list of prices.
     */
    Page<PriceDto> getAllByCurrency(String currency, Pageable pageable);

    /**
     * Find price by product.
     *
     * @param productDto the product.
     * @param pageable   the pageable.
     * @return searched price.
     */
    Page<PriceDto> getPriceByProduct(ProductDto productDto, Pageable pageable);
}
