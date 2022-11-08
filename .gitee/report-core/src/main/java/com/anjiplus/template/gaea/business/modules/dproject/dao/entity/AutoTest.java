package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AutoTest extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer  id;
    //有计算机监控
    private String haveComputerMonitor;
    //有视频监控
    private String haveVideoMonitor;
    //计算机监控运行与否
    private String computerIsWork;
    //视频监控运行与否
    private String videoIsWork;
}
