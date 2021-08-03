package com.ms.blogserver.service.api.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.constant.contexts.ErrorContexts;
import com.ms.blogserver.constant.contexts.URLContexts;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.service.api.FileService;
import com.ms.blogserver.utils.FileUtils;
import com.ms.blogserver.model.vo.FileSimpleVO;
import com.ms.blogserver.model.vo.LogVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    @Override
    public PageInfo<FileSimpleVO> findFileName(BaseDTO dto) {
        try {
            PageHelper.startPage(dto.getPage(),dto.getSize());
            List<FileSimpleVO> files = FileUtils.files(new File(URLContexts.LOG_ABSOLUTE_URL));
            return new PageInfo<>(files);
        } catch (Exception e) {
            throw new CustomException(e.getMessage()+ErrorContexts.FILE_ERR);
        }
    }

    @Override
    public LogVO getLog(String fileName) {
        if (StringUtils.isEmpty(fileName)){
            throw new CustomException(ErrorContexts.NO_FILE);
        }
        String url = URLContexts.LOG_ABSOLUTE_URL +URLContexts.SLASH +fileName;
        LogVO logVO = new LogVO();
        logVO.setFileName(fileName);
        logVO.setContext(FileUtils.loadLog(url));
        logVO.setTime(FileUtils.getTime(new File(url).lastModified()));
        return logVO;
    }

    @Override
    public void deleteFile(String fileName) {
        String url = URLContexts.LOG_ABSOLUTE_URL +URLContexts.SLASH +fileName;
        File file = new File(url);
        if (file.exists()) {
            try {
                boolean delete = file.delete();
                if (!delete){
                    throw new CustomException(ErrorContexts.FILE_NO_DELETE);
                };
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }else {
            throw new CustomException(ErrorContexts.NO_FILE);
        }
    }
}
