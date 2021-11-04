package com.ms.blogserver.controller;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.vo.ArticleCardVO;
import com.ms.blogserver.service.api.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@RestController
@Slf4j
@RequestMapping("/client")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @PostMapping("/article/page")
    public Result<PageInfo<ArticleCardVO>> getPages(BaseDTO dto) {
            try {
            PageInfo<ArticleCardVO> page = homeService.getPage(dto);
            return ResultFactory.buildSuccessResult(page);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

}