package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.CategoryDao;
import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.service.CategoryService;
import cn.iselab.inventory.site.web.data.CategoryVO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    CategoryVO vo=new CategoryVO();
    List<Category> categories=new ArrayList<>();
    Page<Category> categoryPage;
    Pageable pageable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        category.setId(1L);
        category.setName("test");
        category.setSuperId(1L);

        vo.setId(1L);
        vo.setName("test");
        vo.setSuperId(1L);

        categories.add(category);

        categoryPage=new PageImpl<Category>(categories);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_createCategory_when_givenCategory() throws Exception {
        categoryDao.save(category);

        Mockito.verify(categoryDao).save(any(Category.class));
    }

    @Test
    public void should_returnCategories() throws Exception {

        when(categoryDao.findAll(any(Specification.class),any(Pageable.class))).thenReturn(categoryPage);

        Page<Category> result=categoryService.getCategories("test",pageable);

        Assert.assertEquals(category.getId(),result.getContent().get(0).getId());
        Assert.assertEquals(category.getName(),result.getContent().get(0).getName());
        Assert.assertEquals(category.getSuperId(),result.getContent().get(0).getSuperId());
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

        Mockito.verify(categoryDao).save(any(Category.class));
    }

    @Test
    public void should_updateCategory_when_givenCategory() throws Exception {
        categoryService.updateCategory(category,vo);

        Mockito.verify(categoryDao).save(any(Category.class));
    }

}