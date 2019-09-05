package by.profsoft.work;

import by.profsoft.work.dto.CategoryDto;
import by.profsoft.work.facade.CategoryFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan
public class CategoryFacadeTest {
    private Pageable pageable = PageRequest.of(1, 2);
    @Autowired
    CategoryFacade categoryFacade;

    @Test
    public void saveOrUpdate() {
        String name = "test category";
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setNameCategory(name);
        categoryDto = categoryFacade.saveOrUpdate(categoryDto);

        assertThat(categoryDto.getId(), is(greaterThanOrEqualTo(0L)));
        assertEquals(categoryDto.getNameCategory(), name);
    }

    @Test
    public void getCategoryById() {
        String name = "test category";
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setNameCategory(name);
        categoryDto = categoryFacade.saveOrUpdate(categoryDto);

        Long id = categoryDto.getId();

        CategoryDto categoryDtoNew = categoryFacade.getCategoryById(id);

        assertThat(categoryDtoNew.getNameCategory(), is(equalTo(name)));
    }

    @Test
    public void getAllCategory() {
        Page<CategoryDto> categoryDtos = categoryFacade.getAllCategory(pageable);
        System.out.println(categoryDtos.toString());
        assertThat(categoryDtos.getContent().size(), is(greaterThanOrEqualTo(0)));
    }

    @Test
    public void deleteCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setNameCategory("test category");
        categoryDto = categoryFacade.saveOrUpdate(categoryDto);
        Long id = categoryDto.getId();
        categoryFacade.deleteCategory(categoryDto);

        CategoryDto categoryDtoNew = categoryFacade.getCategoryById(id);

        assertNull(categoryDtoNew);
    }

    @Test
    public void getCategoryByName() {
        String name = "C0_0";
        CategoryDto categoryDto = categoryFacade.getCategoryByName(name);
        assertNotNull(categoryDto);
        assertEquals(categoryDto.getNameCategory(), name);
    }

    @Test
    public void getCategoriesByProduct() {
        Page<CategoryDto> categoryDtos = categoryFacade.getCategoriesByProduct(3L, pageable);
        assertEquals(1, categoryDtos.getContent().size());
    }
}
