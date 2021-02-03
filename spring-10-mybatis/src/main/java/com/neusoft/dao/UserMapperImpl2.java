package com.neusoft.dao;

import com.neusoft.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;


import java.util.List;

public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> selectAllUser() {
        return getSqlSession().getMapper(UserMapper.class).selectAllUser();
    }
}
