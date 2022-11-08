package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
/**
@Author soup
@Date 2022/05/07
@Description 管理部门表
*/
@Data
public class ManagementDepartment extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //管理部门服务热线
    private String servicePhoneNumbers;
    //管理部门名字
    private String manageDepartmentName;
    //工程上级主管单位名称
    private String upManageDepartment;
    //管理单位性质
    private String departmentNature;
    //用水满意度
    private Integer useWaterSatified;
}
