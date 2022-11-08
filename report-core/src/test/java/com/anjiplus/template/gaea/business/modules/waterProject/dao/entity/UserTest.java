package com.anjiplus.template.gaea.business.modules.waterProject.dao.entity;

import com.anjiplus.template.gaea.business.modules.waterProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserService userService;
    @Test
    public void getAll(){
//        List<User> users = userMapper.selectList(null);
//        System.out.println(users);
        System.out.println(userService.getAll());
    }

}