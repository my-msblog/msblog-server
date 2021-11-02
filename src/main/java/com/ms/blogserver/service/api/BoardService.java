package com.ms.blogserver.service.api;

import com.ms.blogserver.model.vo.StatisticsVO;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/2
 */

public interface BoardService {

    /**
     * 面板卡片数据
     * @return
     */
    StatisticsVO getValue();
}
