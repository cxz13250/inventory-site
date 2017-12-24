package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.model.Complimentary;
import cn.iselab.inventory.site.service.ComplimentaryService;
import cn.iselab.inventory.site.web.data.ComplimentaryVO;
import cn.iselab.inventory.site.web.data.wrapper.ComplimentaryVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.ComplimentaryLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:59 2017/12/8
 * @Modified By:
 */
@Service
public class ComplimentaryLogicImpl implements ComplimentaryLogic {

    @Autowired
    ComplimentaryService complimentaryService;

    @Autowired
    ComplimentaryVOWrapper complimentaryVOWrapper;

    @Override
    public Page<ComplimentaryVO> getComplimentaries(String keyword, Pageable pageable){
        Page<Complimentary> complimentaries=complimentaryService.getComplimentaries(keyword,pageable);
        return complimentaries.map(new Converter<Complimentary, ComplimentaryVO>() {
            @Override
            public ComplimentaryVO convert(Complimentary complimentary) {
                return complimentaryVOWrapper.wrap(complimentary);
            }
        });
    }

    @Override
    public ComplimentaryVO getComplimentary(long id){
        Complimentary complimentary=complimentaryService.getComplimentary(id);
        if (complimentary == null) {
            throw new HttpBadRequestException("complimentary not exists");
        }
        return complimentaryVOWrapper.wrap(complimentary);
    }

    @Override
    public Long createComplimentary(ComplimentaryVO vo){
        Complimentary complimentary=complimentaryVOWrapper.unwrap(vo);
        complimentary.setCreateTime(new Timestamp(System.currentTimeMillis()));
        complimentary=complimentaryService.createComplimentary(complimentary);
        return complimentary.getId();
    }

    @Override
    public void updateComplimentary(ComplimentaryVO vo){
        Complimentary complimentary=complimentaryService.getComplimentary(vo.getId());
        if (complimentary == null) {
            throw new HttpBadRequestException("complimentary not exists");
        }
        updateInfo(complimentary,vo);
        complimentaryService.updateComplimentary(complimentary);
    }

    @Override
    public void deleteComplimentary(long id){
        Complimentary complimentary=complimentaryService.getComplimentary(id);
        if (complimentary == null) {
            throw new HttpBadRequestException("complimentary not exists");
        }
        complimentary.setDelete(DeleteStatus.IS_DELETE);
        complimentaryService.updateComplimentary(complimentary);
    }

    private void updateInfo(Complimentary complimentary,ComplimentaryVO vo){
        complimentary.setTotal(vo.getTotal());
        complimentary.setCustomId(vo.getCustomId());
        complimentary.setStatus(vo.getStatus());
    }
}
