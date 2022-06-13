package com.ms.blogserver.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.converter.vo.LogsVoConverter;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.core.constant.contexts.ErrorContexts;
import com.ms.blogserver.core.constant.contexts.UrlContexts;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.model.entity.Logs;
import com.ms.blogserver.model.vo.FileVO;
import com.ms.blogserver.service.api.FileService;
import com.ms.blogserver.service.entity.LogsService;
import com.ms.blogserver.utils.FileUtils;
import com.ms.blogserver.model.bo.FileSimpleBO;
import com.ms.blogserver.model.vo.LogVO;
import com.ms.blogserver.utils.PageInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/10
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private LogsService logsService;
    @Override
    public PageInfo<FileVO> findFileName(BaseDTO dto) {
        try {
            List<FileSimpleBO> files = FileUtils.files(new File(UrlContexts.LOG_URL));
            logsService.filterLogs(files);
            PageHelper.startPage(dto.getPage(),dto.getSize());
            List<Logs> logsList = logsService.list(new LambdaQueryWrapper<Logs>()
                    .orderByDesc(Logs::getTime));
            List<FileVO> resList = LogsVoConverter.INSTANCE.toDataList(logsList);
            PageInfo<FileVO> res = new PageInfo<>();
            PageInfoUtil.transform(new PageInfo<>(logsList),res);
            res.setList(resList);
            return res;
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public LogVO getLog(String fileName) {
        if (StringUtils.isEmpty(fileName)){
            throw new CustomException(ErrorContexts.NO_FILE);
        }
        String url = UrlContexts.LOG_URL + UrlContexts.SLASH +fileName;
        LogVO logVO = new LogVO();
        logVO.setFileName(fileName);
        logVO.setContext(FileUtils.loadLog(url));
        logVO.setTime(FileUtils.getTime(new File(url).lastModified()));
        return logVO;
    }

    @Override
    public void deleteFile(String fileName) {
        String url = UrlContexts.LOG_URL + UrlContexts.SLASH +fileName;
        File file = new File(url);
        if (file.exists()) {
            try {
                boolean delete = file.delete();
                if (!delete){
                    throw new CustomException(ErrorContexts.FILE_NO_DELETE);
                }
            } catch (Exception e) {
                throw new CustomException(e);
            }
        }else {
            throw new CustomException(ErrorContexts.NO_FILE);
        }
    }
}
