package com.rainbow.upload.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 *  @Description 文件上传实体
 *  @author liuhu
 *  @Date 2020-06-05 13:48:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文件上传实体")
@TableName("t_attachment")
public class Attachment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 原始文件名
     */
    private String fileName;
    /**
     * 全路径
     */
    private String fullPath;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件域
     */
    private String domainName;
    /**
     * 文件组
     */
    private String groupName;
    /**
     * 远程文件名
     */
    private String remoteName;
    /**
     * 创建时间
     */
    private Date createTime;



}
