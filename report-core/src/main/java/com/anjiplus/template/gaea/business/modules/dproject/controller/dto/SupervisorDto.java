package com.anjiplus.template.gaea.business.modules.dproject.controller.dto;

import com.anji.plus.gaea.curd.dto.GaeaBaseDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
public class SupervisorDto extends GaeaBaseDTO implements Serializable {

    //负责人电话号码
    @NotEmpty
    private String phoneNumber;
    //负责人名字
    @NotEmpty
    private String name;
    //负责人住址
    @NotEmpty
    private String  address;
    //负责人职位
    @NotEmpty
    private String position;
}
