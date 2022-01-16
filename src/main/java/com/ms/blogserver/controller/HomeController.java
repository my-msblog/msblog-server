package com.ms.blogserver.controller;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.base.BaseController;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultFactory;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.model.vo.AnnouncementVO;
import com.ms.blogserver.model.vo.ArticleCardVO;
import com.ms.blogserver.model.vo.HomeCardVO;
import com.ms.blogserver.service.api.HomeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@RestController
@Slf4j
@RequestMapping("/client")
public class HomeController extends BaseController {

    @Autowired
    private HomeService homeService;

    @ApiOperation(value="文章分页")
    @PostMapping("/article/page")
    public Result<PageInfo<ArticleCardVO>> getPages(@RequestBody BaseDTO dto) throws Exception {
        try {
            PageInfo<ArticleCardVO> page = homeService.getPage(dto);
            return ResultFactory.buildSuccessResult(page);
        } catch (Exception e) {
            throw exceptionHandle(e);
        }
    }
    @ApiOperation(value = "主页个人卡片信息统计")
    @GetMapping("/info")
    public Result<HomeCardVO> getMainInfo() throws Exception {
        try {
            HomeCardVO result = homeService.getHomeCard();
            return ResultFactory.buildSuccessResult(result);
        } catch (Exception e) {
            throw exceptionHandle(e);
        }
    }

    @ApiOperation(value = "七天内最新公告接口")
    @GetMapping("/get/announcement")
    public Result<AnnouncementVO> getAnnouncement() throws Exception {
        try {
            AnnouncementVO result = homeService.getAnnouncement();
            return ResultFactory.buildSuccessResult(result);
        } catch (Exception e) {
            throw exceptionHandle(e);
        }
    }

}
