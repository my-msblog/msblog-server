package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class FlowServiceImpl extends ServiceImpl<FlowMapper, Flow> implements FlowService {
}
