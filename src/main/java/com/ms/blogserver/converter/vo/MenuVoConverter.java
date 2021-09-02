package com.ms.blogserver.converter.vo;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.entity.Menu;
import com.ms.blogserver.model.vo.MenuVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@org.mapstruct.Mapper
public interface MenuVoConverter extends Converter<Menu, MenuVO> {

    MenuVoConverter INSTANCE = Mappers.getMapper(MenuVoConverter.class);
}
