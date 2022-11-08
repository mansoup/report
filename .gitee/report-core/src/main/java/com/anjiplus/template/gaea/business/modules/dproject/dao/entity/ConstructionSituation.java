package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
@Author soup
@Date 2022/05/07
@Description 建设情况表
*/
@Data
public class ConstructionSituation extends BaseEntity {
    //主键 设置为自增
    @TableId(type = IdType.AUTO)
    private Integer id;
    //工程投资总额
    private Double totalInvest;
    //国家总投资
    private Double stateInvest;
    //开始供水时间 导入时间是字符串不符合，在数据库里改不了，除非删掉所有数据
    private String startSupplyWaterTime;
    //是否是竣工工程
    private String isFinishProject;
    
}
