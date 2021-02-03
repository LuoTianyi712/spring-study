package com.neusoft.dao;

import com.neusoft.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAllUser();

    int addUser(User user);
    int deleteUserById(@Param("id") int id);
}
