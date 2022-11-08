package com.anjiplus.template.gaea.business.modules.dproject.controller.param;

import com.anji.plus.gaea.annotation.Query;
import com.anji.plus.gaea.constant.QueryEnum;
import com.anji.plus.gaea.curd.params.PageParam;
import lombok.Data;


import java.io.Serializable;
@Data
public class WaterProjectParam extends PageParam implements Serializable {
//    @Query(value = QueryEnum.LIKE)
//    private Integer id;

    /** 供水工程编码 */
    @Query(value = QueryEnum.LIKE)
    private String supplyWaterProjectCode;
    //供水工程名称
    @Query(value = QueryEnum.LIKE)
    private String supplyWaterProjectName;
    @Query(value = QueryEnum.LIKE)
    private String province;
    @Query(value = QueryEnum.LIKE)
    private String city;
    @Query(value = QueryEnum.LIKE)
    private String county;
    @Query(value = QueryEnum.LIKE)
    private String approval;

}
