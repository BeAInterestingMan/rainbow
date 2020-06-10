package com.rainbow.server.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 *  @Description 消息服务实体
 *  @author liuhu
 *  @Date 2020/5/25 15:34
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("消息实体")
public class RainbowMail {

    /**发送人邮件地址*/
    @ApiModelProperty(name = "fromMailAddress",value = "发送人邮件地址",required = true)
    @NotBlank(message = "发送人邮件地址不能为空")
    private String fromMailAddress;

    /**收件人邮件地址*/
    @ApiModelProperty(name = "ToMailAddress",value = "收件人邮件地址",required = true)
    @NotBlank(message = "收件人邮件地址不能为空")
    private String ToMailAddress;

    /**主题*/
    @ApiModelProperty(name = "subject",value = "主题",required = true)
    @NotBlank(message = "主题不能为空")
    private String subject;

    /**内容*/
    @ApiModelProperty(name = "text",value = "内容",required = true)
    @NotBlank(message = "内容不能为空")
    private String text;

}
