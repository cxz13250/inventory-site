package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.GoodsVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.GoodsLogic;
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
 * @Date: Created in 下午8:49 2017/12/4
 * @Modified By:
 */
@RestController
public class GoodsController extends BaseController{

    @Autowired
    GoodsLogic goodsLogic;

    @RequestMapping(value = UrlConstants.API_GOODS,method = RequestMethod.GET)
    public Map<String,Object> getGoods(@RequestParam(name = "keyword", required = false)String keyword,
                                       @RequestParam(name = "sortBy")String sortBy,
                                       HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<GoodsVO> goodsVOS=goodsLogic.getGoodsList(keyword,pageable);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,goodsVOS);
    }

    @RequestMapping(value = UrlConstants.API+"good",method = RequestMethod.GET)
    public Map<String,Object> getGood(@RequestParam(name = "goodsId")Long goodsId){
        try {
            GoodsVO goodsVO=goodsLogic.getGoods(goodsId);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,goodsVO);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.GOODS_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_GOODS,method = RequestMethod.POST)
    public Map<String,Object> createGoods(@RequestBody @NotNull GoodsVO goodsVO){
        Long id = goodsLogic.createGoods(goodsVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, id);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_GOODS,method = RequestMethod.PUT)
    public Map<String,Object> updateGoods(@RequestBody @NotNull GoodsVO goodsVO){
        try {
            goodsLogic.updateGoods(goodsVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.GOODS_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_GOODS,method = RequestMethod.DELETE)
    public Map<String,Object> deleteGoods(@RequestParam(name = "goodsId")Long goodsId){
        try {
            goodsLogic.deleteGoods(goodsId);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.GOODS_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_GOODS + "/purchase",method = RequestMethod.GET)
    public Map<String,Object> getGoodsForPurchase(HttpServletRequest request){
        try {
            List<GoodsVO> vos= goodsLogic.getGoodsForPurchase();
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,vos);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.GOODS_NOT_EXISTS);
        }
    }
}
