package com.anjiplus.template.gaea.business.modules.project.dao.entity.param;

import com.anji.plus.gaea.annotation.Query;
import com.anji.plus.gaea.constant.QueryEnum;
import com.anji.plus.gaea.curd.params.PageParam;
import lombok.Data;

import java.io.Serializable;

@Data
public class SupervisorParam extends PageParam implements Serializable {
    //负责人名字
    @Query(value = QueryEnum.LIKE)
    private String name;
    //负责人电话号码
    @Query(value = QueryEnum.LIKE)
    private String phoneNumber;

}
