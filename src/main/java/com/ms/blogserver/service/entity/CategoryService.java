package com.ms.blogserver.service.entity;

import com.ms.blogserver.core.base.EntityService;
import com.ms.blogserver.mapper.CategoryMapper;
import com.ms.blogserver.model.entity.Category;
import com.ms.blogserver.model.vo.CategoryVO;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/4
 */
public interface CategoryService extends EntityService<Category, CategoryMapper> {

    /**
     * 获取文章分类
     * @param categoryId
     * @return
     */
    String getCategoryByCid(Integer categoryId);

    /**
     * 获取分类列表
     * @return
     */
    List<CategoryVO> getList();
}
