package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.web.data.CustomVO;
import cn.iselab.inventory.site.web.data.GoodsVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:08 2017/12/4
 * @Modified By:
 */
@Service
public class CustomVOWrapper extends BaseWrapper<CustomVO,Custom>{

    @Override
    public CustomVO wrap(Custom custom){
        CustomVO vo=new CustomVO();
        vo.setAddress(custom.getAddress());
        vo.setEmail(custom.getEmail());
        vo.setMobile(custom.getMobile());
        vo.setLevel(custom.getLevel());
        vo.setName(custom.getName());
        vo.setPay(custom.getPay());
        vo.setPostCode(custom.getPostCode());
        vo.setReceive(custom.getReceive());
        vo.setId(custom.getId());
        vo.setReceiveLimit(custom.getReceiveLimit());
        vo.setType(custom.getType());
        vo.setSalesman(custom.getSalesman());
        return vo;
    }

    @Override
    public Custom unwrap(CustomVO vo){
        Custom custom=new Custom();
        custom.setMobile(vo.getMobile());
        custom.setEmail(vo.getEmail());
        custom.setAddress(vo.getAddress());
        custom.setLevel(vo.getLevel());
        custom.setName(vo.getName());
        custom.setPostCode(vo.getPostCode());
        custom.setPay(vo.getPay());
        custom.setReceive(vo.getReceive());
        custom.setReceiveLimit(vo.getReceiveLimit());
        custom.setType(vo.getType());
        custom.setSalesman(vo.getSalesman());
        return custom;
    }
}
