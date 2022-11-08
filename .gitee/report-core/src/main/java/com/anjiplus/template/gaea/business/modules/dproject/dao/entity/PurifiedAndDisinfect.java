package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
@Author soup
@Date 2022/05/07
@Description 净化消毒
*/
@Data
public class PurifiedAndDisinfect extends BaseEntity {
    //主键 设置为自增
    @TableId(type = IdType.AUTO)
    private Integer id;
    //有无净化
    private String isPurified;
    //有无消毒
    private String isDisinfect;
    //净化运行情况
    private String purifiedSituation;
    //消毒运行情况
    private String disinfectSituation;
}
