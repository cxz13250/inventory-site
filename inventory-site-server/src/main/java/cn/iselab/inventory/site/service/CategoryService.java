package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.web.data.CategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:44 2017/11/6
 * @Modified By:
 */
public interface CategoryService {

    Category createCategory(Category category);

    Page<Category> getCategories(String keyword, Pageable pageable);

    Category getCategory(long id);

    List<Category> getCategoryByName(String name);

    void deleteCategory(Category category);

    void updateCategory(Category category, CategoryVO vo);

    List<Category> getCategoriesForGood();

    List<Category> getCategoriesForCategory();
}
