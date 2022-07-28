package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class) // 测试代码以此为配置类
class CommunityApplicationTests implements ApplicationContextAware { // 想得到spring容易，就实现此接口

    private ApplicationContext applicationContext; // 成员变量，记录一下

    // applicationContext就是一个spring容器
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testApplicationContext() {
        System.out.println(applicationContext);

        // 数据库演示
        // 从容器里获取自动装配的Bean
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());

        // 程序的某一块就是想用Hibernate
        // 利用Bean名强制返回Bean
        AlphaDao alphaHibernate = applicationContext.getBean("alphaHibernate", AlphaDao.class); // 默认返回类型为Object，要转型
        System.out.println(alphaHibernate.select());
    }

    @Test
    public void testBeanManagement() {
        // 通过容器获取service
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);

        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    // 在容器里放入别人写的类
    @Test
    public void testBeanConfig() {
        SimpleDateFormat simpleDateFormat =
                applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    // 以上都是主动获取，比较笨拙，重在理解底层

    // 依赖注入，DI，dependency injection

    @Autowired // 也可以放在构造器和setter方法，但本方法常用
    @Qualifier("alphaHibernate")   // 希望注入Hibernate
    private AlphaDao alphaDao; // 获取MyBatisImpl

    @Autowired
    private AlphaService alphaService;

    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Test
    public void testDI() {
        System.out.println(alphaDao.select());
        System.out.println(alphaService);
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
