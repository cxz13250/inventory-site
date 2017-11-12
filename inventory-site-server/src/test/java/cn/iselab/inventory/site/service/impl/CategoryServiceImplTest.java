package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.CategoryDao;
import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.service.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:48 2017/11/6
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryService categoryService=new CategoryServiceImpl();

    @Mock
    private CategoryDao categoryDao;

    Category category=new Category();
    List<Category> categories=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        category.setId(1L);
        category.setName("test");
        category.setSuperId(1L);

        categories.add(category);
    }

    @Test
    public void should_createCategory_when_givenCategory() throws Exception {
        categoryDao.save(category);

        Mockito.verify(categoryDao).save(any(Category.class));
    }

    @Test
    public void should_returnCategories() throws Exception {

        when(categoryDao.findAllCategory()).thenReturn(categories);

        List<Category> result=categoryService.getCategories();

        Assert.assertEquals(category.getId(),result.get(0).getId());
        Assert.assertEquals(category.getName(),result.get(0).getName());
        Assert.assertEquals(category.getSuperId(),result.get(0).getSuperId());
    }

    @Test
    public void should_returnCategory_when_givenCategoryId() throws Exception {
        when(categoryDao.findOne(anyLong())).thenReturn(category);

        Category result=categoryService.getCategory(1L);

        Assert.assertEquals(category.getId(),result.getId());
        Assert.assertEquals(category.getName(),result.getName());
        Assert.assertEquals(category.getSuperId(),result.getSuperId());
    }

    @Test
    public void should_deleteCategory_when_givenCategory() throws Exception {
        categoryService.deleteCategory(category);

        Mockito.verify(categoryDao).delete(any(Category.class));
    }

    @Test
    public void should_updateCategory_when_givenCategory() throws Exception {
        categoryService.updateCategory(category);

        Mockito.verify(categoryDao).save(any(Category.class));
    }

}