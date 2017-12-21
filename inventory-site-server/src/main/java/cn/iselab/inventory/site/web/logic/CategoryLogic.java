package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.web.data.CategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:42 2017/12/5
 * @Modified By:
 */
public interface CategoryLogic {

    Page<CategoryVO> getCategorys(String keyword, Pageable pageable);

    CategoryVO getCategory(Long categoryId);

    Long createCategory(CategoryVO vo);

    void updateCategory(CategoryVO customVO);

    void deleteCategory(Long categoryId);

    List<CategoryVO> getCategorysForGood();

    List<CategoryVO> getCategorysForCategory();
}
