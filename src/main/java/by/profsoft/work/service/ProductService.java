package by.profsoft.work.service;

import by.profsoft.work.model.Category;
import by.profsoft.work.model.Price;
import by.profsoft.work.model.Product;
import by.profsoft.work.repository.IProductRepository;
import by.profsoft.work.service.intarfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service for product.
 */
@Service
public class ProductService implements IProductService {

    private final IProductRepository iProductRepository;

    /**
     * Constructor for product service repository.
     *
     * @param iProductRepository the product repository.
     */
    @Autowired
    public ProductService(final IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    @Override
    public Product saveProduct(final Product product) {
        iProductRepository.save(product);
        return product;
    }

    @Override
    public Page<Product> getAllProducts(final Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }

    @Override
    public void deleteProduct(final Product product) {
        if (product != null) {
            iProductRepository.delete(product);
        }
    }

    @Override
    public Product getProductById(final Long id) {
        return iProductRepository.findById(id).orElse(null);
    }

    @Override
    public Product findByNameProduct(final String name) {
        return iProductRepository.findByNameProduct(name);
    }

    @Override
    public Product findByPrices(final Price price) {
        return iProductRepository.findByPrices(price);
    }

    @Override
    public Page<Product> findByCategories(final Category category, final Pageable pageable) {
        return iProductRepository.findByCategories(category, pageable);
    }
}
