package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.mapper.LogsMapper;
import com.ms.blogserver.model.bo.FileSimpleBO;
import com.ms.blogserver.model.entity.Logs;
import com.ms.blogserver.service.entity.LogsService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/20
 */
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs> implements LogsService{


    @Override
    public void filterLogs(List<FileSimpleBO> list) {
        list.forEach(fileSimpleBO -> {
            Logs logs = baseMapper.selectOne(new LambdaQueryWrapper<Logs>()
                    .eq(Logs::getFileName, fileSimpleBO.getFileName()));
            if (Objects.isNull(logs)){
                Instant instant = fileSimpleBO.getTime().toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                baseMapper.insert(new Logs(fileSimpleBO.getFileName(), LocalDateTime.ofInstant(instant, zoneId)));
            }
        });
    }
}
