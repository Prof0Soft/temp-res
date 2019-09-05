package by.profsoft.work.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for product.
 */
@NoArgsConstructor
@Data
@Entity(name = "Product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String nameProduct;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Price> prices = new ArrayList<>();

    /**
     * Constructor with all dependencies.
     *
     * @param nameProduct the name product.
     * @param prices      the prices.
     * @param categories  the categories.
     */
    public Product(final String nameProduct, final List<Price> prices, final List<Category> categories) {
        this.nameProduct = nameProduct;
        this.prices = prices;
//        this.categories = categories;
    }

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "products")
    private List<Category> categories = new ArrayList<>();

    /**
     * Save - update product
     *
     * @param price the product's prices.
     */
    public void addPriceToProduct(final Price price) {
        this.prices.add(price);
    }
}
