package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.mapper.AnnouncementMapper;
import com.ms.blogserver.model.entity.Announcement;
import com.ms.blogserver.service.entity.AnnouncementService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/16
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {
}
