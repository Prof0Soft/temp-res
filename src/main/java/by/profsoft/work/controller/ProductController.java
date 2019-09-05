package by.profsoft.work.controller;

import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.dto.PriceDto;
import by.profsoft.work.dto.ProductDto;
import by.profsoft.work.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Product controller.
 */
@RestController
@RequestMapping(path = "/product")
@PreAuthorize("hasRole('USER')")
public class ProductController {
    private final ProductFacade productFacade;

    /**
     * Constructor with depend.
     *
     * @param productFacade the product facade.
     */
    @Autowired
    public ProductController(final ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    /**
     * Save or update product.
     *
     * @param productDto the product.
     * @return saved product.
     */
    @GetMapping("/save")
    public ProductDto saveProduct(@RequestBody final ProductDto productDto) {
        return productFacade.saveOrUpdate(productDto);
    }

    /**
     * Mapping all product.
     *
     * @param pageable the pageable set.
     * @return list of product.
     */
    @GetMapping("/all")
    public Page<ProductDto> getAllProduct(final Pageable pageable) {
        return productFacade.getAllProduct(pageable);
    }

    /**
     * Delete product from base.
     *
     * @param productDto the deleting product.
     * @return deleted product.
     */
    @GetMapping("/delete")
    public ResponseEntity<Void> deleteProduct(@RequestBody final ProductDto productDto) {
        productFacade.deleteProduct(productDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Mapping product by id.
     *
     * @param id the id searching product.
     * @return seached product.
     */
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable final Long id) {
        return productFacade.getProductById(id);
    }

    /**
     * Get product by name.
     *
     * @param name the name searching name.
     * @return searched product.
     */
    @GetMapping("/name/{name}")
    public ProductDto getByNameProduct(@PathVariable final String name) {
        return productFacade.getByNameProduct(name);
    }

    /**
     * Get product by price.
     *
     * @param priceDto the price of product.
     * @return seached product.
     */
    @GetMapping("/price")
    public ProductDto getByPrices(@RequestBody final PriceDto priceDto) {
        return productFacade.getByPrices(priceDto);
    }

    /**
     * Get product by category.
     *
     * @param categoryDto the category.
     * @param pageable    the pageable set.
     * @return searched product object.
     */
    @GetMapping("/category")
    public Page<ProductDto> getByCategories(@RequestBody final CategoryDto categoryDto, final Pageable pageable) {
        return productFacade.getByCategories(categoryDto, pageable);
    }
}
