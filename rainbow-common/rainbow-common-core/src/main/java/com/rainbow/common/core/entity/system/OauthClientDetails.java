package com.rainbow.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *  @Description 维护第三方客户端信息实体
 *  @author liuhu
 *  @Date 2020/5/28 10:02
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("oauth_client_details")
@ApiModel("客户端实体")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 421783821058285802L;


    @ApiModelProperty(value="客户端ID",required = true)
    @TableId
    private String clientId;

    @ApiModelProperty(value = "资源id集合")
    private String resourceIds;


    @ApiModelProperty(value = "客户端密码加密",required = true)
    private String clientSecret;


    @ApiModelProperty(value = "权限范围",required = true)
    private String scope;


    @ApiModelProperty(value = "认证模式",required = true)
    private String authorizedGrantTypes;

    @ApiModelProperty("重定向url")
    private String webServerRedirectUri;

    @ApiModelProperty("指定用户的权限范围")
    private String authorities;


    @ApiModelProperty(value = "设置access_token的有效时间",required = true)
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "设置refresh_token有效期",required = true)
    private Integer refreshTokenValidity;

    private Integer additionalInformation;

    private Byte autoapprove;

    @ApiModelProperty(value = "客户端原始密码",required = true)
    private String originSecret;

}
