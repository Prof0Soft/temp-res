package by.profsoft.work.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for category.
 */
@NoArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String nameCategory;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "category_product",
    joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "f_parent")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Category> subCategories = new ArrayList<>();

    /**
     * Constructor for create object.
     * @param nameCategory the name category.
     * @param products the product.
     * @param parent the super category for own category.
     * @param subCategories the sub categories for own category.
     */
    public Category(final String nameCategory, final List<Product> products, final Category parent,
                    final List<Category> subCategories) {
        this.nameCategory = nameCategory;
        this.products = products;
        this.parent = parent;
        this.subCategories = subCategories;
    }

    /**
     * Add subCategory.
     * @param subCategory the subCategory.
     */
    public void addSubCategory(final Category subCategory) {
        subCategories.add(subCategory);
    }

    /**
     * Add product to category.
     * @param product the product.
     */
    public void addProduct(final Product product) {
        products.add(product);
    }
}
