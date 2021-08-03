package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.mapper.TagMapper;
import com.ms.blogserver.service.entity.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public List<Tag> getAllTag() {
        return baseMapper.selectList(null);
    }

    @Override
    public Tag getTagById(Long id) {
        return baseMapper.selectById(id);
    }
}
