package com.ms.blogserver.model.vo;

import com.ms.blogserver.model.entity.Menu;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MenuVO {
    private String path;
    private String nameZh;
    private String component;
    private String icon;
    private List<Menu> children;

}
