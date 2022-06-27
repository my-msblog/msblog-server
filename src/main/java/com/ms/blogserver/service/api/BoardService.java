package com.ms.blogserver.service.api;

import com.ms.blogserver.core.base.BaseService;
import com.ms.blogserver.model.vo.StatisticsVO;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/2
 */

public interface BoardService extends BaseService {

    /**
     * 面板卡片数据
     *
     * @return {@link StatisticsVO}
     */
    StatisticsVO getValue();
}
