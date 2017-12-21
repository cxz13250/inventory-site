package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.CategoryVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.CategoryLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:41 2017/12/5
 * @Modified By:
 */

@RestController
public class CategoryController extends BaseController{

    @Autowired
    CategoryLogic categoryLogic;

    @RequestMapping(value = UrlConstants.API+"categories",method = RequestMethod.GET)
    public Map<String,Object> getCategories(@RequestParam(value = "keyword",required = false)String keyword,
                                            @RequestParam(value = "sortBy")String sortBy,
                                            HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<CategoryVO> categorys=categoryLogic.getCategorys(keyword,pageable);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,categorys);
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY,method = RequestMethod.GET)
    public Map<String,Object> getCategory(@RequestParam(name = "categoryId")Long categoryId){
        try {
            CategoryVO categoryVO=categoryLogic.getCategory(categoryId);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,categoryVO);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.CATEGORY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY,method = RequestMethod.POST)
    public Map<String,Object> createCategory(@RequestBody @NotNull CategoryVO categoryVO){
        Long id = categoryLogic.createCategory(categoryVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, id);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY,method = RequestMethod.PUT)
    public Map<String,Object> updateCategory(@RequestBody @NotNull CategoryVO categoryVO){
        try {
            categoryLogic.updateCategory(categoryVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.CATEGORY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY,method = RequestMethod.DELETE)
    public Map<String,Object> deleteCategory(@RequestParam(name = "categoryId")Long categoryId){
        try {
            categoryLogic.deleteCategory(categoryId);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.CATEGORY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API+"categories/goods",method = RequestMethod.GET)
    public Map<String,Object> getCategoryForGood(){
        List<CategoryVO> categoryVOS=categoryLogic.getCategorysForGood();
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,categoryVOS);
    }

    @RequestMapping(value = UrlConstants.API+"categories/category",method = RequestMethod.GET)
    public Map<String,Object> getCategoryForCategory(){
        List<CategoryVO> categoryVOS=categoryLogic.getCategorysForCategory();
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,categoryVOS);
    }
}
