package by.profsoft.work.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for category.
 */
@NoArgsConstructor
@Data
public class CategoryDto {
    private Long id;
    private String nameCategory;
    private List<Long> idProducts = new ArrayList<>();
    private Long idParent;
    private List<Long> idSubCategories = new ArrayList<>();
}
