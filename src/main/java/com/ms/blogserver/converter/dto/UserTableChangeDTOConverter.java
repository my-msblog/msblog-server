package com.ms.blogserver.converter.dto;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.entity.User;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper()
public interface UserTableChangeDTOConverter extends Converter<User, UserTableChangeDTO> {

    UserTableChangeDTOConverter INSTANCE = Mappers.getMapper(UserTableChangeDTOConverter.class);

    @Override
    User fromData(UserTableChangeDTO dto);
}
