package com.yoyling.test;

import com.yoyling.domain.Account;
import com.yoyling.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 */
public class AccountServiceTest {
    @Test
    public void testFinAll() {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        //3.执行方法
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }
}
