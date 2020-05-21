package com.rainbow.common.core.entity.system;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  @Description 菜单实体
 *  @author liuhu
 *  @Date 2020/5/15 13:07
 */
@Data
@TableName("t_menu")
@ApiModel("菜单实体")
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

    /**
     * 菜单
     */
    public static final String TYPE_MENU = "0";
    /**
     * 按钮
     */
    public static final String TYPE_BUTTON = "1";
    public static final Long TOP_MENU_ID = 0L;
    private static final long serialVersionUID = 7187628714679791771L;
    /**
     * 菜单/按钮ID
     */
    @TableId(value = "MENU_ID", type = IdType.AUTO)
    @ApiModelProperty(name = "menuId",value = "菜单/按钮ID")
    @Excel(name = "菜单/按钮ID", orderNum = "0", width = 15)
    private Long menuId;

    /**
     * 上级菜单ID
     */
    @TableField("PARENT_ID")
    @ApiModelProperty(name = "parentId",value = "上级菜单ID")
    @Excel(name = "上级菜单ID", orderNum = "1", width = 15)
    private Long parentId;

    /**
     * 菜单/按钮名称
     */
    @TableField("MENU_NAME")
    @ApiModelProperty(name = "menuName",value = "菜单/按钮名称")
    @Excel(name = "菜单/按钮名称", orderNum = "2", width = 15)
    private String menuName;

    /**
     * 菜单URL
     */
    @TableField("PATH")
    @ApiModelProperty(name = "path",value = "菜单URL")
    @Excel(name = "菜单URL", orderNum = "3", width = 15)
    private String path;

    /**
     * 对应 Vue组件
     */
    @TableField("COMPONENT")
    @ApiModelProperty(name = "component",value = "对应 Vue组件")
    @Excel(name = "对应 Vue组件", orderNum = "4", width = 15)
    private String component;

    /**
     * 权限标识
     */
    @TableField("PERMS")
    @ApiModelProperty(name = "perms",value = "权限标识")
    @Excel(name = "权限标识", orderNum = "5", width = 15)
    private String perms;

    /**
     * 图标
     */
    @TableField("ICON")
    @ApiModelProperty(name = "icon",value = "图标")
    @Excel(name = "图标", orderNum = "6", width = 15)
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @TableField("TYPE")
    @ApiModelProperty(name = "icon",value = "类型 0菜单 1按钮")
    @Excel(name = "类型", orderNum = "7", width = 15,replace = {"按钮_1","菜单_0"})
    private String type;

    /**
     * 排序
     */
    @TableField("ORDER_NUM")
    @ApiModelProperty(name = "orderNum",value = "排序")
    private Integer orderNum;

    @TableField(exist = false)
    @ApiModelProperty(name = "childMenus",value = "子菜单集合")
    private List<Menu> childMenus;


    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    @ApiModelProperty(name = "createTime",value = "创建时间")
    @Excel(name = "创建时间", orderNum = "8", width = 15,exportFormat="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    @ApiModelProperty(name = "modifyTime",value = "修改时间")
    @ExcelIgnore
    private Date modifyTime;

    @TableField(exist = false)
    @ExcelIgnore
    private  String createTimeFrom;

    @TableField(exist = false)
    @ExcelIgnore
    private  String createTimeTo;

}