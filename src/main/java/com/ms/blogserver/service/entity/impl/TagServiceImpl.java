package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.converter.vo.TagVoConverter;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.mapper.TagMapper;
import com.ms.blogserver.model.vo.TagVO;
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
    public List<TagVO> getTagList() {
        List<Tag> tagList = baseMapper.selectList(new QueryWrapper<>());
        return TagVoConverter.INSTANCE.toDataList(tagList);
    }
}
