package com.rainbow.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *  @Description 部门实体表
 *  @author liuhu
 *  @Date 2020/5/27 15:15
 */
@ApiModel("部门实体")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_dept")
public class Dept implements Serializable {

    public static final Long TOP_DEPT_ID = 0L;
    private static final long serialVersionUID = -7790334862410409053L;
    @TableId(value = "DEPT_ID", type = IdType.AUTO)
    @ApiModelProperty(name = "deptId",value = "部门ID")
    private Long deptId;

    @TableField(value = "PARENT_ID")
    @ApiModelProperty(name = "parentId",value = "父Id")
    private Long parentId;

    @ApiModelProperty(name = "deptName",value = "父部门名称")
    private String deptName;

    @TableField(value = "ORDER_NUM")
    private Integer orderNum;

    @TableField(value = "CREATE_TIME")
    private Date createTime;

    @TableField(value = "MODIFY_TIME")
    private Date modifyTime;

    @TableField(exist = false)
    private  String createTimeFrom;

    @TableField(exist = false)
    private transient String createTimeTo;

}