package com.ms.blogserver.controller;

import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.service.api.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/10
 */
@RestController
@Slf4j
@RequestMapping(value = "/log")
public class LogController extends BaseController {

    @Autowired
    private FileService fileService;

    /**
     * 所有日志文件列表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/find/list")
    public Result getAllLog(BaseDTO dto) throws Exception {
        try {
            return ResultFactory.buildSuccessResult(fileService.findFileName(dto));
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 获取日志文件内容
     *
     * @param fileName 文件名
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/get/context")
    public Result getLog(String fileName) throws Exception {
        try {
            return ResultFactory.buildSuccessResult(fileService.getLog(fileName));
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/delete")
    public Result deleteLog(String fileName) throws Exception {
        try {
            fileService.deleteFile(fileName);
            return ResultFactory.buildSuccessResult("");
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }
}
