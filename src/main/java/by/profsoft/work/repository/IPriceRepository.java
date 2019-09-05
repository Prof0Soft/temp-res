package by.profsoft.work.repository;

import by.profsoft.work.model.Price;
import by.profsoft.work.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for prices.
 */
@Repository
public interface IPriceRepository extends JpaRepository<Price, Long> {

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
