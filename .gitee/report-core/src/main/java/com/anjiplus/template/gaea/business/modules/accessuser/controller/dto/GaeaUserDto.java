package com.anjiplus.template.gaea.business.modules.accessuser.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@ApiModel(value = "用户登录")
@Data
public class GaeaUserDto {
    /**
    @Author soup
    @Date 2022/09/03
    @Description @NotBlank 对应的是@Validated搭配使用，在controller中参数接收然后验证


    */
    @ApiModelProperty(value = "登录名")
    @NotBlank
    private String loginName;

    @ApiModelProperty(value = "密码")
    @NotBlank
    private String password;

    /** 真实用户 */
    private String realName;

    /** 登录成功后的 */
    private String token;

    /** 用户所拥有的权限合集 */
    private List<String> authorities;
}
