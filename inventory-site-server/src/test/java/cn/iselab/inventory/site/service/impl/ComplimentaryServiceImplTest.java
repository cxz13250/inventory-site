package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.ComplimentaryDao;
import cn.iselab.inventory.site.model.Complimentary;
import cn.iselab.inventory.site.service.ComplimentaryService;
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
 * @Date: Created in 下午11:32 2017/11/12
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class ComplimentaryServiceImplTest {

    @InjectMocks
    private ComplimentaryService complimentaryService=new ComplimentaryServiceImpl();

    @Mock
    private ComplimentaryDao complimentaryDao;

    Complimentary complimentary=new Complimentary();
    List<Complimentary> complimentaries=new ArrayList<>();
    Page<Complimentary> complimentaryPage;
    Pageable pageable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        complimentary.setContent("test");
        complimentary.setId(1L);
        complimentary.setCustomId(1L);

        complimentaries.add(complimentary);

        complimentaryPage=new PageImpl<Complimentary>(complimentaries);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_createComplimentary_when_givenComplimentary() throws Exception {
        when(complimentaryDao.save(any(Complimentary.class))).thenReturn(complimentary);

        Complimentary result=complimentaryService.createComplimentary(complimentary);

        Assert.assertEquals(result.getId(),complimentary.getId());
        Assert.assertEquals(result.getContent(),complimentary.getContent());
        Assert.assertEquals(result.getCustomId(),complimentary.getCustomId());
    }

    @Test
    public void should_returnComplimentaries_givenKeyword() throws Exception {
        when(complimentaryDao.findAll(any(Specifications.class),any(Pageable.class))).thenReturn(complimentaryPage);

        Page<Complimentary> result=complimentaryService.getComplimentaries("test",pageable);

        Assert.assertEquals(complimentary,result.getContent().get(0));
    }

    @Test
    public void should_returnComplimentary_givenId() throws Exception {
        when(complimentaryDao.findOne(anyLong())).thenReturn(complimentary);

        Complimentary result=complimentaryService.getComplimentary(1L);

        Assert.assertEquals(result.getId(),complimentary.getId());
        Assert.assertEquals(result.getContent(),complimentary.getContent());
        Assert.assertEquals(result.getCustomId(),complimentary.getCustomId());
    }

    @Test
    public void updateComplimentary() throws Exception {
        complimentaryService.updateComplimentary(complimentary);

        Mockito.verify(complimentaryDao).save(any(Complimentary.class));
    }

    @Test
    public void deleteComplimentary() throws Exception {
        complimentaryService.deleteComplimentary(complimentary);

        Mockito.verify(complimentaryDao).delete(any(Complimentary.class));
    }

}