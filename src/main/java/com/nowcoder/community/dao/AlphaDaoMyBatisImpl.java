package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author pssBerg
 * @version 1.0
 * @date 2022 06 04, 9:56
 */

@Repository // 如果只有Repository，满足接口类型的有两个，有歧义
@Primary // 更高的优先级装配，把Hibernate替换为MyBatis
public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
