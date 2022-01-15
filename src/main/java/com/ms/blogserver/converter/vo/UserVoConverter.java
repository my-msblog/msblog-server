package com.ms.blogserver.converter.vo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.UserVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/31
 */
@org.mapstruct.Mapper
public interface UserVoConverter extends Converter<User, UserVO> {

    UserVoConverter INSTANCE = Mappers.getMapper(UserVoConverter.class);

}
