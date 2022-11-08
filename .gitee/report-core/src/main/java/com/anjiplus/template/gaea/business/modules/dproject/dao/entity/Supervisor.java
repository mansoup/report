package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.annotation.Unique;
import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.anjiplus.template.gaea.business.code.ResponseCode;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
/**
@Author soup
@Date 2022/05/07
@Description 负责人表
*/
@Data
public class Supervisor extends BaseEntity {
    @TableId(type = IdType.AUTO)
    @Unique(code = ResponseCode.SET_CODE_ISEXIST)
    private Long id;
    //管理部门Id外键
    private Integer  managementDepartmentId;
    //负责人电话号码
    private String phoneNumber;
    //负责人名字
    private String name;
    //负责人住址
    private String  address;
    //负责人职位
    private String position;

}
