package com.anjiplus.template.gaea.business.modules.dproject.dao.entity;

import com.anji.plus.gaea.annotation.Unique;
import com.anji.plus.gaea.curd.entity.BaseEntity;
import com.anji.plus.gaea.curd.entity.GaeaBaseEntity;
import com.anjiplus.template.gaea.business.code.ResponseCode;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class WaterProject extends GaeaBaseEntity {
    @ApiModelProperty(value = "供水工程编码")
    @Unique(code = ResponseCode.SET_CODE_ISEXIST)
    private String supplyWaterProjectCode;

    private String imgUrl;
//    //主键 设置为自增
//    @TableId(type = IdType.AUTO)
//    private Integer id;
    //工程投资总额
    private Double totalInvest;
    //国家总投资
    private Double stateInvest;
    //开始供水时间
    private String startSupplyWaterTime;
    //是否是竣工工程
    private String isFinishProject;
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
    //有无净化
    private String isPurified;
    //有无消毒
    private String isDisinfect;
    //净化运行情况
    private String purifiedSituation;
    //消毒运行情况
    private String disinfectSituation;
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
    private Integer waterChargeRecoveryRate;
    //水质化实验室建立与否
    private String isBuildLab;
    //水质化实验室运行状况
    private String isWork;
    //出厂水日检指标
    private String waterIndexOutFactory;
    //水质结果30
    private String waterQualityResult;
    //有计算机监控
    private String haveComputerMonitor;
    //有视频监控
    private String haveVideoMonitor;
    //计算机监控运行与否
    private String computerIsWork;
    //视频监控运行与否
    private String videoIsWork;
    //管理部门服务热线
    private String servicePhoneNumbers;
    //管理部门名字
    private String manageDepartmentName;
    //工程上级主管单位名称
    private String upManageDepartment;
    //管理单位性质
    private String departmentNature;
    //用水满意度
    private Integer useWaterSatified;
    //负责人名字40
    private String supervisorName;
    //负责人住址
    private String address;
    //负责人职位
    private String position;
    //工程省份位置
    private String province;
    //工程地级市
    private String city;
    //工程县区地址
    private String county;
    //供水工程名称
    private String supplyWaterProjectName;
    //供水工程位置
    private String location;
    //供水工程状态性质
    private String projectSituation;
    //供水保证率
    private Integer supplyWaterRate;
    //填报年份 部署时数据库改成date50
    private String writeTime;
    //设计供水规模
    private Integer designSupplyScale;
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
    //工程审核 1代表需要审核而且改变成0才能被再次编辑，0默认不需要审核或者还没审核
    private Integer approval;
}
