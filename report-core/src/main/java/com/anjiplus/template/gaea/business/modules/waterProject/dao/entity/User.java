package com.anjiplus.template.gaea.business.modules.waterProject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import lombok.Data;

@Data
public class User extends BaseEntity {
    private Integer id;
    private String name;
    private Integer age;
}
