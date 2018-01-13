package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.model.SaleDetail;
import cn.iselab.inventory.site.service.SaleDetailService;
import cn.iselab.inventory.site.web.data.SaleDetailVO;
import cn.iselab.inventory.site.web.data.wrapper.SaleDetailVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.SaleDetailLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:37 2017/12/8
 * @Modified By:
 */
@Service
public class SaleDetailLogicImpl implements SaleDetailLogic{

    @Autowired
    SaleDetailService saleDetailService;

    @Autowired
    SaleDetailVOWrapper saleDetailVOWrapper;

    @Override
    public SaleDetailVO getSaleDetail(Long startTime,Long endTime){
//        SaleDetail saleDetail=saleDetailService.getSaleDetail(id);
        SaleDetail saleDetail = new SaleDetail();
        if (saleDetail == null) {
            throw new HttpBadRequestException("sale detail not exists");
        }
        return saleDetailVOWrapper.wrap(saleDetail);
    }

    @Override
    public Page<SaleDetailVO> getSaleDetails(Pageable pageable,Long startTime,Long endTime,String goodName) throws Exception{
        Page<SaleDetail> details=saleDetailService.getSaleDetails(goodName,pageable);
        return details.map(new Converter<SaleDetail, SaleDetailVO>() {
            @Override
            public SaleDetailVO convert(SaleDetail saleDetail) {
                SaleDetailVO vo= saleDetailVOWrapper.wrap(saleDetail);
                vo.setCreateTime(random(startTime,endTime));
                return vo;
            }
        });
    }

    @Override
    public List<SaleDetailVO> getSaleDetailsForExcel(long startTime, long endTime){
        List<SaleDetail> details=saleDetailService.getAll();
        List<SaleDetailVO> vos=new ArrayList<>();
        for (SaleDetail detail:details){
            SaleDetailVO vo= saleDetailVOWrapper.wrap(detail);
            vo.setCreateTime(random(startTime,endTime));
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public Long createSaleDetail(SaleDetailVO vo){
        SaleDetail saleDetail=saleDetailVOWrapper.unwrap(vo);
        saleDetail=saleDetailService.createSaleDetail(saleDetail);
        return saleDetail.getId();
    }

    @Override
    public void deleteSaleDetail(long id){
        SaleDetail saleDetail=saleDetailService.getSaleDetail(id);
        if (saleDetail == null) {
            throw new HttpBadRequestException("sale detail not exists");
        }
        saleDetail.setDelete(DeleteStatus.IS_DELETE);
        saleDetailService.updateSaleDetail(saleDetail);
    }

    private Long random(long begin,long end){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            long startTime = format.parse(format.format(begin)).getTime();
            long endTime = format.parse(format.format(end)).getTime();
            long rtn = begin + (long) (Math.random() * (endTime - startTime));
            return rtn;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
