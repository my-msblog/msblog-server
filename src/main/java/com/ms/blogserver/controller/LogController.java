package com.ms.blogserver.controller;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.base.BaseController;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultFactory;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.model.vo.FileVO;
import com.ms.blogserver.model.vo.LogVO;
import com.ms.blogserver.service.api.FileService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("所有日志文件列表")
    @PostMapping(value = "/page")
    public Result<PageInfo<FileVO>> getAllLog(@RequestBody BaseDTO dto) throws Exception {
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
    @ApiOperation("获取日志文件内容")
    @PostMapping(value = "/get/context")
    public Result<LogVO> getLog(@RequestBody String fileName) throws Exception {
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
    @ApiOperation("删除文件指定文件")
    @PostMapping(value = "/delete")
    public Result<String> deleteLog(@RequestBody String fileName) throws Exception {
        try {
            fileService.deleteFile(fileName);
            return ResultFactory.buildSuccessResult("");
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }
}
