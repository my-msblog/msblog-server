package com.ms.blogserver.controller;

import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.model.vo.StatisticsVO;
import com.ms.blogserver.service.api.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/2
 */
@RestController
@Slf4j
@RequestMapping("/board")
public class BoardController extends BaseController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/card/value")
    public Result getValue(){
        try {
            StatisticsVO res = boardService.getValue();
            return ResultFactory.buildSuccessResult(res);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(),e);
        }
    }
}
