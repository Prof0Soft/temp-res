package by.profsoft.work.service;

import by.profsoft.work.model.Price;
import by.profsoft.work.model.Product;
import by.profsoft.work.repository.IPriceRepository;
import by.profsoft.work.service.intarfaces.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service for prices.
 */
@Service
public class PriceService implements IPriceService {

    private final IPriceRepository iPriceRepository;

    /**
     * Constructor with depend.
     *
     * @param iPriceRepository the price repository.
     */
    @Autowired
    public PriceService(final IPriceRepository iPriceRepository) {
        this.iPriceRepository = iPriceRepository;
    }

    @Override
    public Page<Price> getAllPrices(final Pageable pageable) {
        Page<Price> prices = iPriceRepository.findAll(pageable);
        return prices;
    }

    @Override
    public Price savePrice(final Price price) {
        iPriceRepository.save(price);
        return price;
    }

    @Override
    public Price getPriceById(final Long id) {
        return iPriceRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePrice(final Price price) {
        iPriceRepository.delete(price);
    }

    @Override
    public Page<Price> findAllByCurrency(final String currency, final Pageable pageable) {
        return iPriceRepository.findAllByCurrency(currency, pageable);
    }

    @Override
    public Page<Price> findByProduct(final Product product, final Pageable pageable) {
        return iPriceRepository.findByProduct(product, pageable);
    }

    @Override
    public Page<Price> findAllByPriceGreaterThanAndPriceLessThan(final Double priceOne, final Double priceTwo,
                                                                 final Pageable pageable) {
        return iPriceRepository.findAllByPriceGreaterThanAndPriceLessThan(priceOne, priceTwo, pageable);
    }
}
