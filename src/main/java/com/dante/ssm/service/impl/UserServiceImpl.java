package com.dante.ssm.service.impl;

import com.dante.ssm.bean.User;
import com.dante.ssm.dao.IUserDao;
import com.dante.ssm.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }
}
