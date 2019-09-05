package by.profsoft.work;

import by.profsoft.work.model.Category;
import by.profsoft.work.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan
public class CategoryServiceTest {

    private Pageable pageable = new QPageRequest(1, 2);
    @Autowired
    CategoryService categoryService;

    @Test
    public void saveCategory() {
        String nameCategory = "test category";
        Category category = new Category();
        category.setNameCategory(nameCategory);

        categoryService.saveCategory(category);

        Long id = category.getId();

        category = categoryService.getCategoryById(id);
        assertThat(category.getNameCategory(), is(greaterThanOrEqualTo(nameCategory)));
    }

    @Test
    public void getAllCategories() {
        Page<Category> categories = categoryService.getAllCategories(pageable);
        assertThat(categories.getContent().size(), is(greaterThanOrEqualTo(0)));
    }

    @Test
    public void getCategoryById() {
        Category category = new Category();
        category.setNameCategory("test category");
        Long idCategory = categoryService.saveCategory(category).getId();

        categoryService.deleteCategory(category);
        category = categoryService.getCategoryById(idCategory);
        Assert.assertNull(category);
    }

    @Test
    public void deleteCategory() {
        Category category = categoryService.getCategoryById(1L);
        assertThat(category.getId(), is(greaterThanOrEqualTo(1L)));
    }
}