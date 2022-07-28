package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pssBerg
 * @version 1.0
 * @date 2022 06 26, 12:24
 */

@Mapper
public interface DiscussPostMapper {

    // 查询功能，查询方法
    // 查到多条数据，所以返回的数据类型应该是集合
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit); // userId用于开发个人主页功能

    // 分页
    // @Param是用来给参数取别名，如果需要动态的条件，并且这个方法有且仅有一个参数，就必须取别名
    // 如果只有一个参数，并且在<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);


}
