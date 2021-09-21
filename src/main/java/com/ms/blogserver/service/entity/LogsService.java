package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.bo.FileSimpleBO;
import com.ms.blogserver.model.entity.Logs;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/20
 */
public interface LogsService extends IService<Logs> {

    /**
     * 过滤已存在的日志
     * @param list
     */
    void filterLogs(List<FileSimpleBO> list);
}
