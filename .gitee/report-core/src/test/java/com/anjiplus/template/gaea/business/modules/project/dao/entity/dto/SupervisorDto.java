package com.anjiplus.template.gaea.business.modules.project.dao.entity.dto;

import com.anji.plus.gaea.curd.dto.GaeaBaseDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class SupervisorDto  implements Serializable {
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
