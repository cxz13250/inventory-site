package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.service.CustomService;
import cn.iselab.inventory.site.web.data.CustomVO;
import cn.iselab.inventory.site.web.data.wrapper.CustomVOWrapper;
import cn.iselab.inventory.site.web.logic.CustomLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:49 2017/12/4
 * @Modified By:
 */

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class CustomLogicImplTest {

    @InjectMocks
    CustomLogic customLogic=new CustomLogicImpl();

    @Mock
    CustomService customService;

    @Mock
    CustomVOWrapper customVOWrapper;

    Page<CustomVO> customVOPage;
    Page<Custom> customPage;
    List<CustomVO> customVOS=new ArrayList<>();
    List<Custom> customs=new ArrayList<>();
    CustomVO vo=new CustomVO();
    Custom custom=new Custom();
    Pageable pageable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        custom.setId(1L);
        custom.setName("test");
        custom.setAddress("test");
        custom.setEmail("test@qq.com");
        custom.setMobile("test");

        customs.add(custom);

        vo.setId(1L);
        vo.setName("test");
        vo.setAddress("test");
        vo.setEmail("test@qq.com");
        vo.setMobile("test");

        customVOS.add(vo);

        customPage=new PageImpl<Custom>(customs);

        customVOPage=new PageImpl<CustomVO>(customVOS);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_retunCustoms_when_CustomsExists() throws Exception {
        when(customService.getCustoms(anyString(),any(Pageable.class))).thenReturn(customPage);
        when(customVOWrapper.wrap(any(Custom.class))).thenReturn(vo);

        Page<CustomVO> result=customLogic.getCustoms("test",pageable);
        Assert.assertEquals(vo.getName(),result.getContent().get(0).getName());
        Assert.assertEquals(vo.getAddress(),result.getContent().get(0).getAddress());
        Assert.assertEquals(vo.getEmail(),result.getContent().get(0).getEmail());
    }

    @Test
    public void should_returnCustom_when_givenCustomId() throws Exception {
        when(customService.getCustom(anyLong())).thenReturn(custom);
        when(customVOWrapper.wrap(any(Custom.class))).thenReturn(vo);

        CustomVO result=customLogic.getCustom(1L);
        Assert.assertEquals(vo.getName(),result.getName());
        Assert.assertEquals(vo.getAddress(),result.getAddress());
        Assert.assertEquals(vo.getEmail(),result.getEmail());
    }

    @Test
    public void should_createCustom_when_givenCustom() throws Exception {
        when(customVOWrapper.unwrap(any(CustomVO.class))).thenReturn(custom);
        when(customService.createCustom(any(Custom.class))).thenReturn(1L);

        Long result=customLogic.createCustom(vo);

        Assert.assertEquals((Long)custom.getId(),result);
    }

    @Test
    public void should_updateCustom_when_givenCustom() throws Exception {
        when(customService.getCustom(anyLong())).thenReturn(custom);

        customLogic.updateCustom(vo);

        Mockito.verify(customService).updateCustom(any(Custom.class),any(CustomVO.class));
    }

    @Test
    public void should_deleteCustom_when_givenCustomId() throws Exception {
        when(customService.getCustom(anyLong())).thenReturn(custom);

        customLogic.deleteCustom(1L);

        Mockito.verify(customService).deletCustom(any(Custom.class));
    }
}