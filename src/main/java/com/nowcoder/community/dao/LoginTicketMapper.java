package com.nowcoder.community.dao;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * @author pssBerg
 * @version 1.0
 * @date 2022 08 22, 11:43
 */

@Mapper
public interface LoginTicketMapper {

    // 插入一个凭证，增加数据
    // 一般返回影响的行数
    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) " +
            "values(#{userId},#{ticket},#{status},#{expired}) " // 每句话后加个空格
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    // 查询方法
    @Select({
            "select id,user_id,ticket,status,expired " +
            "from login_ticket where ticket=#{ticket} "
    })
    LoginTicket selectByTicket(String ticket);

    // 一般不删除，都是修改状态
    // 演示动态sql
    @Update({
            // "<script>" +
            "update login_ticket set status=#{status} where ticket=#{ticket} "
            // "<if test=\"ticket!=null\">" +
            // "and 1=1" +
            // "</if>" +
            // "</script>"
    })
    int updateStatus(String ticket, int status);
}
