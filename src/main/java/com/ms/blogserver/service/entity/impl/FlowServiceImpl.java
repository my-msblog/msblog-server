package com.ms.blogserver.service.entity.impl;

import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.mapper.FlowMapper;
import com.ms.blogserver.model.entity.Flow;
import com.ms.blogserver.service.entity.FlowService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhh
 * @time: 2021/10/30
 */
@Service
public class FlowServiceImpl extends EntityServiceImpl<Flow,FlowMapper> implements FlowService {
}
