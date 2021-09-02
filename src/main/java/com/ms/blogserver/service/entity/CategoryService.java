package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.entity.Category;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/4
 */
public interface CategoryService extends IService<Category> {

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
    List<Category> getList();
}
