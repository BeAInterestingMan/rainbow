package com.rainbow.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  @Description 角色表
 *  @author liuhu
 *  @Date 2020/5/12 16:31
 */
@Data
@TableName("t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -1714476694755654924L;

    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;

    @TableField(value = "ROLE_NAME")
    private String roleName;

    @TableField(value = "REMARK")
    private String remark;

    @TableField(value = "CREATE_TIME")
    private Date createTime;

    @TableField(value = "MODIFY_TIME")
    private Date modifyTime;

    /**角色ID集合*/
    @TableField(exist = false)
    private String menuIds;

}