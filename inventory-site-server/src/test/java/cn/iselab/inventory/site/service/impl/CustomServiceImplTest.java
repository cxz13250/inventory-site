package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.CustomDao;
import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.service.CustomService;
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
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:19 2017/11/7
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class CustomServiceImplTest {

    @InjectMocks
    private CustomService customService=new CustomServiceImpl();

    @Mock
    private CustomDao customDao;

    Custom custom=new Custom();
    List<Custom> customs=new ArrayList<>();
    Page<Custom> customPage;
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

        customPage=new PageImpl<Custom>(customs);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_createCustom_when_givenCustom() throws Exception {
        when(customDao.save(any(Custom.class))).thenReturn(custom);

        long result=customService.createCustom(custom);

        Assert.assertEquals(custom.getId(),result);
    }

    @Test
    public void should_returnCustom_when_givenCustomId() throws Exception {
        when(customDao.findOne(anyLong())).thenReturn(custom);

        Custom result=customService.getCustom(1L);

        Assert.assertEquals(custom.getId(),result.getId());
        Assert.assertEquals(custom.getAddress(),result.getAddress());
        Assert.assertEquals(custom.getEmail(),result.getEmail());
        Assert.assertEquals(custom.getName(),result.getName());
        Assert.assertEquals(custom.getMobile(),result.getMobile());
    }

    @Test
    public void should_returnCustoms_when_givenKeyword() throws Exception {
        when(customDao.findAll(any(Specifications.class),any(Pageable.class))).thenReturn(customPage);

        Page<Custom> result=customService.getCustoms("test",pageable);

        Assert.assertEquals(custom,result.getContent().get(0));
    }

    @Test
    public void should_updateCustom_when_givenCustom() throws Exception {
        customService.updateCustom(custom);

        Mockito.verify(customDao).save(any(Custom.class));
    }

    @Test
    public void should_deleteCustom_when_givenCustom() throws Exception {
        customService.deletCustom(custom);

        Mockito.verify(customDao).delete(any(Custom.class));
    }

}