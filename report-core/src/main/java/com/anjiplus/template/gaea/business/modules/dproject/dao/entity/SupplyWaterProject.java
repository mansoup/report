package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
/**
@Author soup
@Date 2022/05/07
@Description 供水工程表
*/
@Data
public class SupplyWaterProject extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //供水工程名称
    private String name;
    //供水工程位置
    private String location;
    //供水工程状态性质
    private String projectSituation;
    //供水保证率
    private Integer supplyWaterRate;
    //填报年份;部署应该设置为date
    private String writeTime;
    //设计供水规模
    private String designSupplyScale;
    //年实际供水
    private Integer actualSupplyWaterAnnual;
    //地表水源名
    private String surfaceWaterName;
    //管网漏损率
    private Integer pipeLackRate;
    //供水工程管理人数
    private Integer projectManageNums;
    //工程经度
    private BigDecimal longitude;
    //工程纬度 https://blog.csdn.net/qq_39805362/article/details/117329099 应该设置一公里差
    private BigDecimal latitude;
    //管理部门Id外键
    private Integer managementDepartmentId;
    //建设情况id外键
    private Integer constructionSituationId;
    //建设情况id外键
    private Integer waterSourceId;
    //净化消毒Id外键
    private Integer purifiedAndDisinfectId;
    //供水情况Id外键
    private Integer supplySituationId;
    //水费水价Id外键
    private Integer  waterPriceId;
    //位置Id外键
    private Integer locationId;
    //水质化实验室Id外键
    private Integer waterQualityLabId;
    //自动化检测Id外键
    private Integer autoTestId;

}
