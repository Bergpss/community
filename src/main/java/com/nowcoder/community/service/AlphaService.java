package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author pssBerg
 * @version 1.0
 * @date 2022 06 04, 10:04
 */

@Service // 通过容器进行管理
@Scope("singleton") // 默认参数，单例，项目中常用
// @Scope("prototype") // 每次访问bean都会创建一个新的实例
public class AlphaService {

    // 注入AlphaDao
    @Autowired
    private AlphaDao alphaDao;

    // 构造器
    public AlphaService() {
        System.out.println("构造器：实例化AlphaService");
    }

    // 增加Bean初始化方法
    @PostConstruct // 该方法在构造器之后调用
    public void init() {
        System.out.println("初始化AlphaService");
    }

    @PreDestroy // 在销毁之前调用
    public void destroy() {
        System.out.println("销毁AlphaService");
    }


    // 模拟查询业务
    public String find() {
        return alphaDao.select();
    }
}
