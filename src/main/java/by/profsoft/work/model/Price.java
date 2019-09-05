package by.profsoft.work.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Data
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "Price")
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long id;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String currency;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
