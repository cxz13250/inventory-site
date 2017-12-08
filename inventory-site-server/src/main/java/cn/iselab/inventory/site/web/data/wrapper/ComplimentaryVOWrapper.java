package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Complimentary;
import cn.iselab.inventory.site.web.data.ComplimentaryVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:04 2017/12/8
 * @Modified By:
 */
@Service
public class ComplimentaryVOWrapper extends BaseWrapper<ComplimentaryVO,Complimentary>{

    @Override
    public ComplimentaryVO wrap(Complimentary complimentary){
        ComplimentaryVO vo=new ComplimentaryVO();
        vo.setContent(complimentary.getContent());
        vo.setTotal(complimentary.getTotal());
        vo.setCustomId(complimentary.getCustomId());
        vo.setCreateTime(complimentary.getCreateTime().getTime());
        return vo;
    }

    @Override
    public Complimentary unwrap(ComplimentaryVO vo){
        Complimentary complimentary=new Complimentary();
        complimentary.setContent(vo.getContent());
        complimentary.setCustomId(vo.getCustomId());
        complimentary.setTotal(vo.getTotal());
        return complimentary;
    }
}
