package com.mybatis.rewrite.dao;

import com.mybatis.rewrite.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-05-14 23:03
 */

public interface UserDao {

    public User queryUser(@Param("id") Long id);

    public int insertUser(User user);

}
