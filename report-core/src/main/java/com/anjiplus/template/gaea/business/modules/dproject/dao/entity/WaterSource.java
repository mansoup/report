package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
/**
@Author soup
@Date 2022/05/07
@Description 水源表
*/
@Data
public class WaterSource extends BaseEntity {
    //主键 设置为自增
    @TableId(type = IdType.AUTO)
    private Integer id;
    //水源类型
    private String waterSourceType;
    //水井个数
    private Integer wellNums;
    //水质质量（优良差）
    private String waterQuality;
    //是否建立水质保护区
    private String isProtectArea;
    //取水许可证
    private String isWaterIntakeLegal;
    //卫生许可证10
    private String isLegalInHygiene;
}
