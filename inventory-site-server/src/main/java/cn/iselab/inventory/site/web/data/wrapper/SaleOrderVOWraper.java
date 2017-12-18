package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.web.data.SaleOrderVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:05 2017/12/5
 * @Modified By:
 */

@Service
public class SaleOrderVOWraper extends BaseWrapper<SaleOrderVO,SaleOrder>{

    @Override
    public SaleOrderVO wrap(SaleOrder order){
        SaleOrderVO vo=new SaleOrderVO();
        vo.setDiscount(order.getDiscount());
        vo.setExtra(order.getExtra());
        vo.setOperator(order.getOperator());
        vo.setCustomId(order.getCustomId());
        vo.setRepository(order.getRepository());
        vo.setTotal(order.getTotal());
        vo.setDiscount(order.getDiscount());
        vo.setStatus(order.getStatus());
        vo.setType(order.isType());
        vo.setSalesman(order.getSaleman());
        vo.setCreateTime(order.getCreateTime().getTime());
        return vo;
    }

    @Override
    public SaleOrder unwrap(SaleOrderVO vo){
        SaleOrder order=new SaleOrder();
        order.setDiscount(vo.getDiscount());
        order.setExtra(vo.getExtra());
        order.setOperator(vo.getOperator());
        order.setCustomId(vo.getCustomId());
        order.setRepository(vo.getRepository());
        order.setTotal(vo.getTotal());
        order.setDiscount(vo.getDiscount());
        order.setStatus(vo.getStatus());
        order.setType(vo.getType());
        order.setSaleman(vo.getSalesman());
        return order;
    }
}
