package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 菜单实体类
 * @author: zhh
 * @time: 2021/5/21
 */
@Data
@TableName(value = "ms_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = -8911958141094981098L;
    @TableId
    private Integer id;
    private String path;
    private String name;
    private String nameZh;
    private String zh;
    private String component;
    private Integer parentId;
    private String icon;
    @TableLogic
    private int deleted;
    @TableField(exist = false)
    private List<Menu> children;

    public Menu() {
    }
}
