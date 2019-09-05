package by.profsoft.work.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO product.
 */
@Data
public class ProductDto {
    private Long id;
    private String nameProduct;
    private List<PriceDto> prices = new ArrayList<>();
    private List<Long> idCategories = new ArrayList<>();
}
