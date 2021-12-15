package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.converter.vo.CategoryVoConverter;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.entity.Category;
import com.ms.blogserver.mapper.CategoryMapper;
import com.ms.blogserver.model.vo.CategoryVO;
import com.ms.blogserver.service.entity.ArticleService;
import com.ms.blogserver.service.entity.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/4
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public String getCategoryByCid(Integer categoryId) {
        Category category = baseMapper.selectOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getCategoryId,categoryId));
        return category.getCategory();
    }

    @Override
    public List<CategoryVO> getList() {
        List<Category> categoryList = baseMapper.selectList(new QueryWrapper<>());
        List<CategoryVO> res = CategoryVoConverter.INSTANCE.toDataList(categoryList);
        res.forEach(item ->{
            List<Article> articleList = articleService.lambdaQuery().eq(Article::getType, item.getCategoryId()).list();
            item.setCount(articleList.size());
        });
        return res;
    }
}
