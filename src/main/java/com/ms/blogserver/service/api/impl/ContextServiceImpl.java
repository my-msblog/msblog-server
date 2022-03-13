package com.ms.blogserver.service.api.impl;

import com.ms.blogserver.core.base.BaseOptions;
import com.ms.blogserver.model.entity.Category;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.service.api.ContextService;
import com.ms.blogserver.service.entity.CategoryService;
import com.ms.blogserver.service.entity.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2022/3/13
 */
@Service
public class ContextServiceImpl implements ContextService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Override
    public List<BaseOptions<Integer, String>> getCategory() {
        List<BaseOptions<Integer, String>> resList = new ArrayList<>();
        List<Category> categoryList = categoryService.list();
        categoryList.forEach(category -> {
            BaseOptions<Integer, String> options = new BaseOptions<>();
            options.setLabel(category.getCategory());
            options.setValue(category.getCategoryId());
            resList.add(options);
        });
        return resList;
    }

    @Override
    public List<BaseOptions<Long, String>> getTag() {
        List<BaseOptions<Long, String>> resList = new ArrayList<>();
        List<Tag> tagList = tagService.list();
        tagList.forEach(tag -> {
            resList.add(new BaseOptions<Long, String>(tag.getId(),tag.getNameZh()));
        });
        return resList;
    }

}
