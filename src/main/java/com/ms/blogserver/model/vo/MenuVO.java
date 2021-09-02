package com.ms.blogserver.model.vo;

import com.ms.blogserver.converter.vo.MenuVoConverter;
import com.ms.blogserver.model.entity.Menu;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@ToString
public class MenuVO {
    private String path;
    private String nameZh;
    private String component;
    private String icon;
    private List<MenuVO> children;

    public MenuVO (){}

    public MenuVO (Menu menu){
        this.path = menu.getPath();
        this.nameZh = menu.getNameZh();
        this.component = menu.getComponent();
        this.icon = menu.getIcon();
        this.children = MenuVoConverter.INSTANCE.toDataList(menu.getChildren());
    }
}
