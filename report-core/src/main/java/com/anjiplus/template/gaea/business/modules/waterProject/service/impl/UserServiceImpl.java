package com.anjiplus.template.gaea.business.modules.waterProject.service.impl;

import com.anjiplus.template.gaea.business.modules.waterProject.dao.entity.User;
import com.anjiplus.template.gaea.business.modules.waterProject.dao.entity.UserMapper;
import com.anjiplus.template.gaea.business.modules.waterProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getAll() {
        List<User> users = userMapper.selectList(null);
        return users;
    }
}
