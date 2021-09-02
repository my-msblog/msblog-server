package com.ms.blogserver.converter.dto;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.dto.UserDTO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/31
 */
@org.mapstruct.Mapper()
public interface UserDtoConverter extends Converter<User, UserDTO> {

    UserDtoConverter INSTANCE = Mappers.getMapper(UserDtoConverter.class);

}
