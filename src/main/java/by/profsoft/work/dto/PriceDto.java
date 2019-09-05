package by.profsoft.work.dto;

import by.profsoft.work.utils.DoubleContextualSerializer;
import by.profsoft.work.utils.annatation.Precision;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * DTO price.
 */
@Data
public class PriceDto {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonSerialize(using = DoubleContextualSerializer.class)
    @Precision(precision = 3)
    private Double price;
    private String currency;
    private Long idProduct;
}
