package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
@Author soup
@Date 2022/05/07
@Description 管理表
*/
@Data
public class Manage extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer  id;
    private Integer  managementDepartmentId;
    private Integer  supplyWaterProjectId;
}
