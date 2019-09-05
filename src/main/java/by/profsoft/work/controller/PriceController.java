package by.profsoft.work.controller;

import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.facade.interfaces.IPriceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Price controller.
 */
@RestController
@RequestMapping("/price")
//@PreAuthorize("hasRole('USER')")
public class PriceController {
    private final IPriceFacade priceFacade;

    /**
     * Constructor with depends.
     *
     * @param priceFacade the price facade.
     */
    @Autowired
    public PriceController(final IPriceFacade priceFacade) {
        this.priceFacade = priceFacade;
    }

    /**
     * Save or update price.
     *
     * @param priceDto the price.
     * @return saved price.
     */
    @GetMapping("/save")
    public PriceDto savePrice(@RequestBody final PriceDto priceDto) {
        return priceFacade.saveOrUpdate(priceDto);
    }

    /**
     * Save or update price.
     *
     * @param priceDto the price.
     * @return saved price.
     */
    @GetMapping("/create")
    public ResponseEntity<Void> createPrice(@RequestBody final PriceDto priceDto) {
        priceFacade.saveOrUpdate(priceDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Mapping all prices.
     *
     * @param pageable the pageable set.
     * @return list of prices.
     */
    @GetMapping("/all")
    public Page<PriceDto> getAllPrice(final Pageable pageable) {
        return priceFacade.getAllPrice(pageable);
    }

    /**
     * Mapping price by id.
     *
     * @param id the id searching price.
     * @return seached price.
     */
    @GetMapping("/{id}")
    public PriceDto getPriceById(@PathVariable final Long id) {
        return priceFacade.getPriceById(id);
    }

    /**
     * Delete price from base.
     *
     * @param priceDto the deleting price.
     * @return deleted price.
     */
    @GetMapping("/delete")
    public ResponseEntity<Void> deletePrice(@RequestBody final PriceDto priceDto) {
        priceFacade.deletePrice(priceDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Get all prices with searched currency.
     *
     * @param currency the searching currency.
     * @param pageable the pageable set.
     * @return seached list prices
     */
    @GetMapping("/currency/{currency}")
    public Page<PriceDto> getAllByCurrency(@PathVariable final String currency, final Pageable pageable) {
        return priceFacade.getAllByCurrency(currency, pageable);
    }

    /**
     * Get prices list by product.
     *
     * @param productDto the product for searching prices.
     * @param pageable   the pageable set.
     * @return searched list of prices.
     */
    @GetMapping("/product")
    public Page<PriceDto> getByProduct(@RequestBody final ProductDto productDto, final Pageable pageable) {
        return priceFacade.getPriceByProduct(productDto, pageable);
    }

    /**
     * Get list of prices between two values.
     *
     * @param priceOne the ferst value of searching price.
     * @param priceTwo the second value of searching price.
     * @param pageable the pageable set.
     * @return searched list of prices.
     */
    @GetMapping("/between/{priceOne}-{priceTwo}")
    public Page<PriceDto> getAllByPriceGreaterThanAndPriceLessThan(@PathVariable final Double priceOne,
                                                                   @PathVariable final Double priceTwo,
                                                                   final Pageable pageable) {
        return priceFacade.getPricesBetweenPrice(priceOne, priceTwo, pageable);
    }
}
