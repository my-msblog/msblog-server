package com.ms.blogserver.service.api;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.vo.ArticleCardVO;
import com.ms.blogserver.model.vo.HomeCardVO;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
public interface HomeService {

    /**
     * 文章分页
     * @param dto
     * @return
     */
    PageInfo<ArticleCardVO> getPage(BaseDTO dto);

    /**
     * 获取主页信息
     * @return
     */
    HomeCardVO getHomeCard();
}
