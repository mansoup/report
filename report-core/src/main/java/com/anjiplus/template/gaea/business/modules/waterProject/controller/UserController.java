package com.anjiplus.template.gaea.business.modules.waterProject.controller;

import com.anji.plus.gaea.bean.ResponseBean;
import com.anjiplus.template.gaea.business.modules.waterProject.dao.entity.User;
import com.anjiplus.template.gaea.business.modules.waterProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mapreport")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/queryAllUser")
    public ResponseBean queryAllUser() {
        return ResponseBean.builder().data(userService.getAll()).build();
    }
}
