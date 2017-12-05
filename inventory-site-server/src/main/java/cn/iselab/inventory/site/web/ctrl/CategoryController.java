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
    public Map<String,Object> getCategories(HttpServletRequest request){
        List<CategoryVO> categorys=categoryLogic.getCategorys();
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
    public Map<String,Object> createAccount(@RequestBody @NotNull CategoryVO categoryVO){
        Long id = categoryLogic.createCategory(categoryVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, id);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY,method = RequestMethod.PUT)
    public Map<String,Object> updateAccount(@RequestBody @NotNull CategoryVO categoryVO){
        try {
            categoryLogic.updateCategory(categoryVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.CATEGORY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY,method = RequestMethod.DELETE)
    public Map<String,Object> deleteAccount(@RequestParam(name = "categoryId")Long categoryId){
        try {
            categoryLogic.deleteCategory(categoryId);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.CATEGORY_NOT_EXISTS);
        }
    }
}
