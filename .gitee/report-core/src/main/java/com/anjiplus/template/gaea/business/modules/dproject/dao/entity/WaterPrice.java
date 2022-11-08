package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
@Author soup
@Date 2022/05/07
@Description 水费水价表
*/
@Data
public class WaterPrice extends BaseEntity {
    //主键 设置为自增
    @TableId(type = IdType.AUTO)
    private Integer id;
    //全成本水价
    private Double fullCostWaterPrice;
    //运行成本价
    private Double operateCostWaterPrice;
    //居民生活执行水价
    private Double householdWaterPrice;
    //计费方式
    private String chargeMethod;
    //收支情况
    private String incomeExpenditureSituation;
    //水费回收率
    private String waterChargeRecoveryRate;
}
