package by.profsoft.work.service;

import by.profsoft.work.model.Category;
import by.profsoft.work.model.Product;
import by.profsoft.work.repository.ICategoryRepository;
import by.profsoft.work.service.intarfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service for category.
 */
@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository iCategoryRepository;

    /**
     * Constructor with category repository depend.
     *
     * @param iCategoryRepository the category repository.
     */
    @Autowired
    public CategoryService(final ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    @Override
    public Page<Category> findAllByProducts(final Product product, final Pageable pageable) {
        return iCategoryRepository.findAllByProducts(product, pageable);
    }

    @Override
    public Category saveCategory(final Category category) {
        iCategoryRepository.save(category);
        return category;
    }

    @Override
    public Page<Category> getAllCategories(final Pageable pageable) {
        return iCategoryRepository.findAll(pageable);
    }

    @Override
    public Category findByNameCategory(final String name) {
        return iCategoryRepository.findByNameCategory(name);
    }

    @Override
    public Category getCategoryById(final Long id) {
        return iCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCategory(final Category category) {
        iCategoryRepository.delete(category);
    }
}
