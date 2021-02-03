package com.neusoft.dao;

import com.neusoft.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> selectAllUser() {

        User user = new User(7, "小王", "159");

        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);

        mapper.addUser(user);
        mapper.deleteUserById(8);

        return mapper.selectAllUser();
    }

    @Override
    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUserById(id);
    }
}
