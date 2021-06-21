package com.ms.blogserver.service;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.vo.FileSimpleVO;
import com.ms.blogserver.model.vo.LogVO;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/10
 */
public interface FileService {

    PageInfo<FileSimpleVO> findFileName(BaseDTO dto);

    LogVO getLog(String fileName);

    void deleteFile(String fileName);
}
