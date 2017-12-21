package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.service.CategoryService;
import cn.iselab.inventory.site.web.data.CategoryVO;
import cn.iselab.inventory.site.web.data.wrapper.CategoryVOWrapper;
import cn.iselab.inventory.site.web.logic.CategoryLogic;
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
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午4:00 2017/12/5
 * @Modified By:
 */

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class CategoryLogicImplTest {

    @InjectMocks
    CategoryLogic categoryLogic=new CategoryLogicImpl();

    @Mock
    CategoryService categoryService;

    @Mock
    CategoryVOWrapper categoryVOWrapper;


    List<CategoryVO> vos=new ArrayList<>();
    List<Category> categories=new ArrayList<>();
    CategoryVO vo=new CategoryVO();
    Category category=new Category();
    Category category1=new Category();
    Page<CategoryVO> categoryVOPage;
    Page<Category> categoryPage;
    Pageable pageable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        category.setId(1L);
        category.setName("test");
        category.setSuperId(0L);

        category1.setId(2L);
        category1.setName("test");
        category1.setSuperId(1L);

        categories.add(category1);

        vo.setId(2L);
        vo.setName("test");
        vo.setSuperId(1L);
        vo.setSuperName("test");

        vos.add(vo);

        categoryVOPage=new PageImpl<CategoryVO>(vos);
        categoryPage=new PageImpl<Category>(categories);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_returnCategories_when_CategoriesExist() throws Exception {
        when(categoryService.getCategories(anyString(),any(Pageable.class))).thenReturn(categoryPage);
        when(categoryVOWrapper.wrap(any(Category.class))).thenReturn(vo);
        when(categoryService.getCategory(anyLong())).thenReturn(category);

        Page<CategoryVO> result=categoryLogic.getCategorys("test",pageable);
        Assert.assertEquals(vo.getId(),result.getContent().get(0).getId());
        Assert.assertEquals(vo.getName(),result.getContent().get(0).getName());
        Assert.assertEquals(vo.getSuperName(),result.getContent().get(0).getSuperName());
    }

    @Test
    public void should_returnCategory_when_givenCategoryId() throws Exception {
        when(categoryService.getCategory(anyLong())).thenReturn(category1);
        when(categoryVOWrapper.wrap(any(Category.class))).thenReturn(vo);

        CategoryVO result=categoryLogic.getCategory(2L);

        Assert.assertEquals(vo.getId(),result.getId());
        Assert.assertEquals(vo.getName(),result.getName());
        Assert.assertEquals(vo.getSuperName(),result.getSuperName());
    }

    @Test
    public void should_createCategory_when_givenCategory() throws Exception {
        when(categoryVOWrapper.unwrap(any(CategoryVO.class))).thenReturn(category);
        when(categoryService.createCategory(any(Category.class))).thenReturn(category);

        Long result=categoryLogic.createCategory(vo);

        Assert.assertEquals((Long)category.getId(),result);
    }

    @Test
    public void should_updateCategory_when_givenCategory() throws Exception {
        when(categoryService.getCategory(anyLong())).thenReturn(category);

        categoryLogic.updateCategory(vo);

        Mockito.verify(categoryService).updateCategory(any(Category.class),any(CategoryVO.class));
    }

    @Test
    public void should_deleteCategory_when_givenCategory() throws Exception {
        when(categoryService.getCategory(anyLong())).thenReturn(category);

        categoryLogic.deleteCategory(1L);

        Mockito.verify(categoryService).deleteCategory(any(Category.class));
    }

}