package com.ms.blogserver.converter.dto;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.entity.User;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@org.mapstruct.Mapper()
public interface UserTableChangeDtoConverter extends Converter<User, UserTableChangeDTO> {

    UserTableChangeDtoConverter INSTANCE = Mappers.getMapper(UserTableChangeDtoConverter.class);
}
