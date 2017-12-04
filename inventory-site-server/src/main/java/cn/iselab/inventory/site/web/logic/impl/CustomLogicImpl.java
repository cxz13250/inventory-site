package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.service.CustomService;
import cn.iselab.inventory.site.web.data.CustomVO;
import cn.iselab.inventory.site.web.data.wrapper.CustomVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.CustomLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:05 2017/12/4
 * @Modified By:
 */
@Service
public class CustomLogicImpl implements CustomLogic{

    @Autowired
    CustomService customService;

    @Autowired
    CustomVOWrapper customVOWrapper;

    @Override
    public Page<CustomVO> getCustoms(String keyword, Pageable pageable){
        Page<Custom> customs=customService.getCustoms(keyword,pageable);
        return customs.map(new Converter<Custom, CustomVO>() {
            @Override
            public CustomVO convert(Custom custom) {
                return customVOWrapper.wrap(custom);
            }
        });
    }

    @Override
    public CustomVO getCustom(Long customId){
        Custom custom=customService.getCustom(customId);
        if(custom==null){
            throw new HttpBadRequestException("custom not exists");
        }
        return customVOWrapper.wrap(custom);
    }

    @Override
    public Long createCustom(CustomVO customVO){
        Custom custom=customVOWrapper.unwrap(customVO);
        return customService.createCustom(custom);
    }

    @Override
    public void updateCustom(CustomVO customVO){
        Custom custom=customService.getCustom(customVO.getId());
        if(custom==null){
            throw new HttpBadRequestException("custom not exists");
        }
        customService.updateCustom(custom,customVO);
    }

    @Override
    public void deleteCustom(Long customId){
        Custom custom=customService.getCustom(customId);
        if(custom==null){
            throw new HttpBadRequestException("custom not exists");
        }
        customService.deletCustom(custom);
    }
}
