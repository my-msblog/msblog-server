package com.ms.blogserver.converter.vo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.entity.Logs;
import com.ms.blogserver.model.vo.FileVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/20
 */
@org.mapstruct.Mapper
public interface LogsVoConverter extends Converter<Logs, FileVO> {

    LogsVoConverter INSTANCE = Mappers.getMapper(LogsVoConverter.class);
}
