package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author pssBerg
 * @version 1.0
 * @date 2022 06 04, 9:52
 */

@Repository("alphaHibernate") // 每个Bean都有名字，默认的名字是类名首字母小写alphaDanHibernateImpl，改名
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}
