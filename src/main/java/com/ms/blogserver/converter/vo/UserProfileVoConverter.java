package com.ms.blogserver.converter.vo;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.UserProfileVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/8
 */
@org.mapstruct.Mapper
public interface UserProfileVoConverter extends Converter<User, UserProfileVO> {

    UserProfileVoConverter INSTANCE = Mappers.getMapper(UserProfileVoConverter.class);
}
