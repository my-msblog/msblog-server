package com.ms.blogserver.converter.vo;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.entity.Menu;
import com.ms.blogserver.model.vo.MenuVO;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface MenuVOConverter extends Converter<Menu, MenuVO> {

    MenuVOConverter INSTANCE = Mappers.getMapper(MenuVOConverter.class);
}
