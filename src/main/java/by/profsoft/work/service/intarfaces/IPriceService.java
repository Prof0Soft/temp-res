package by.profsoft.work.service.intarfaces;

import by.profsoft.work.model.Price;
import by.profsoft.work.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface for price.
 */
public interface IPriceService {
    /**
     * Finde all prices from database.
     *
     * @param pageable the pageable.
     * @return list of all prices.
     */
    Page<Price> getAllPrices(Pageable pageable);

    /**
     * Add new prices to database.
     *
     * @param price the prices.
     * @return prices object.
     */
    Price savePrice(Price price);

    /**
     * Find price by id.
     *
     * @param id the id price.
     * @return price object.
     */
    Price getPriceById(Long id);

    /**
     * Delete price from database.
     *
     * @param price the price object.
     */
    void deletePrice(Price price);

    /**
     * Find all prices by currency.
     *
     * @param currency the seaching currency.
     * @param pageable the pageable.
     * @return searched list of prices.
     */
    Page<Price> findAllByCurrency(String currency, Pageable pageable);

    /**
     * Find price by product.
     *
     * @param product  the product.
     * @param pageable the pageable.
     * @return searched price.
     */
    Page<Price> findByProduct(Product product, Pageable pageable);

    /**
     * Find price beetwen two prices.
     *
     * @param priceOne the first price.
     * @param priceTwo the second price.
     * @param pageable the pageable.
     * @return list of searched prices.
     */
    Page<Price> findAllByPriceGreaterThanAndPriceLessThan(Double priceOne, Double priceTwo, Pageable pageable);

}
