package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Location extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer  id;
    //工程省份位置
    private String province;
    //工程地级市
    private String city;
    //工程县区地址
    private String county;
}
