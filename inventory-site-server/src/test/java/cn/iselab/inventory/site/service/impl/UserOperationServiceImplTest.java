package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.UserOperationDao;
import cn.iselab.inventory.site.model.UserOperation;
import cn.iselab.inventory.site.service.UserOperationService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:21 2017/11/6
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class UserOperationServiceImplTest {

    @InjectMocks
    private UserOperationService userOperationService=new UserOperationServiceImpl();

    @Mock
    private UserOperationDao userOperationDao;

    UserOperation operation=new UserOperation();
    List<UserOperation> operations=new ArrayList<>();
    Page<UserOperation> operationPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        operation.setId(1L);
        operation.setOperation("test");
        operation.setUserId(1L);
        operation.setUserId(1L);

        operations.add(operation);

        operationPage=new PageImpl<UserOperation>(operations);
    }

    @Test
    public void should_createOperation_when_givenOperation() throws Exception {
        userOperationService.create(operation);

        Mockito.verify(userOperationDao).save(any(UserOperation.class));
    }

    @Test
    public void should_returnOperations_when_givenKeyword() throws Exception {

        when(userOperationDao.findAll(any(Specification.class),any(Pageable.class))).thenReturn(operationPage);

        Page<UserOperation> result=userOperationService.getUserOperations(anyString(),any(Pageable.class));

        Assert.assertEquals(operations.get(0),result.getContent().get(0));
    }

    @Test
    public void delete() throws Exception {
        userOperationService.delete(operation);

        Mockito.verify(userOperationDao).delete(any(UserOperation.class));
    }

}