package com.rainbow.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 421783821058285802L;

    @TableId(value = "client_id")
    @NotBlank(message = "{required}")
    private String clientId;

    @TableField("resource_ids")
    private String resourceIds;

    @TableField("client_secret")
    @NotBlank(message = "{required}")
    private String clientSecret;

    @TableField("scope")
    @NotBlank(message = "{required}")
    private String scope;

    @TableField("authorized_grant_types")
    @NotBlank(message = "{required}")
    private String authorizedGrantTypes;

    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;

    @TableField("authorities")
    private String authorities;

    @TableField("access_token_validity")
    @NotNull(message = "{required}")
    private Integer accessTokenValidity;

    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @TableField("autoapprove")
    private Byte autoapprove;

    @TableField("origin_secret")
    private String originSecret;

}
