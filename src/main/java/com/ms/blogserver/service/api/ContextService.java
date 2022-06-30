package com.ms.blogserver.service.api;

import com.ms.blogserver.core.base.BaseOptions;
import com.ms.blogserver.core.base.BaseService;
import com.ms.blogserver.model.dto.ArticleCommitDTO;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2022/3/13
 */
public interface ContextService extends BaseService {


    /**
     * 文章分类列表
     *
     * @return {@link List}<{@link BaseOptions}<{@link Integer}, {@link String}>>
     */
    List<BaseOptions<Integer, String>> getCategory();

    /**
     * 标签列表
     *
     * @return {@link List}<{@link BaseOptions}<{@link Integer}, {@link String}>>
     */
    List<BaseOptions<Long, String>> getTag();

    /**
     * 文章提交
     *
     * @param dto dto
     */
    void commit(ArticleCommitDTO dto);
}
