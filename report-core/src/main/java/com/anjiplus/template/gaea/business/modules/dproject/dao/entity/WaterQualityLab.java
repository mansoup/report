package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
@Author soup
@Date 2022/05/07
@Description 水质化实验室表

*/
@Data
public class WaterQualityLab extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer  id;
    //水质化实验室建立与否
    private String isBuildLab;
    //水质化实验室运行状况
    private String isWork;
    //出厂水日检指标
    private String waterIndexOutFactory;
    //水质结果30
    private String waterQualityResult;
}
