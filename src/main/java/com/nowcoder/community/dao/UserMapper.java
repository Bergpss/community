package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pssBerg
 * @version 1.0
 * @date 2022 06 23, 10:39
 */

// @Repository
@Mapper
public interface UserMapper {

    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);
    int insertUser(User user); // 返回插入数据的行数
    int updateStatus(int id, int status); // 返回修改的行数
    int updateHeader(int id, String headerUrl);
    int updatePassword(int id, String password);
}
