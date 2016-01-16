package com.ssm.service.impl;

import com.ssm.dao.SzUserMapper;
import com.ssm.dao.UserMapper;
import com.ssm.domain.SzUser;
import com.ssm.domain.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xujinchao on 2015/10/23.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private SzUserMapper szUserMapper;

    @Resource
    private UserMapper userMapper;

    public User getUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SzUser getSzUserById(int userId) {
        return szUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int addSzUser(SzUser szUser) {
        return szUserMapper.insert(szUser);
    }
}
