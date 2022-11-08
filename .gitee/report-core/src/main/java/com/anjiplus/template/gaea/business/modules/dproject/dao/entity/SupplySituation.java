package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
@Author soup
@Date 2022/05/07
@Description 供水情况表
*/
@Data
public class SupplySituation extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //总供水人数
    private Integer totalNumsOfPeople;
    //供水到户人数
    private Integer waterToHomeNums;
    //供水到师生人数
    private Integer waterToSchoolNums;
    //供水到贫困人数
    private Integer waterToPoorNums;
    //供水到贫困户人数
    private Integer waterToPoorFamilyNums;
    //解决的饮水型氟超标人数20
    private Integer solvedExceedFuNums;
}
