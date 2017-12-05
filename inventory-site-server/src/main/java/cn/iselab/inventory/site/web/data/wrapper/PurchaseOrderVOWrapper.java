package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.PurchaseOrder;
import cn.iselab.inventory.site.web.data.PurchaseOrderVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午6:02 2017/12/5
 * @Modified By:
 */
@Service
public class PurchaseOrderVOWrapper extends BaseWrapper<PurchaseOrderVO,PurchaseOrder>{

    @Override
    public PurchaseOrderVO wrap(PurchaseOrder order){
        PurchaseOrderVO vo=new PurchaseOrderVO();
        vo.setExtra(order.getExtra());
        vo.setNumber(order.getNumber());
        vo.setOperator(order.getOperator());
        vo.setRepository(order.getRepository());
        vo.setStatus(order.getStatus());
        vo.setCreateTime(order.getCreateTime().getTime());
        vo.setSupplier(order.getSupplier());
        vo.setTotal(order.getTotal());
        return vo;
    }

    @Override
    public PurchaseOrder unwrap(PurchaseOrderVO vo){
        PurchaseOrder order=new PurchaseOrder();
        order.setOperator(vo.getOperator());
        order.setRepository(vo.getRepository());
        order.setSupplier(vo.getSupplier());
        order.setExtra(vo.getExtra());
        order.setTotal(vo.getTotal());
        return order;
    }
}
