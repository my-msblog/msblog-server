package com.ms.blogserver.service.api;

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

    /**
     * 日志文件分页
      * @param dto
     * @return
     */
    PageInfo<FileSimpleVO> findFileName(BaseDTO dto);

    /**
     * 文件名获取日志内容
     * @param fileName
     * @return
     */
    LogVO getLog(String fileName);

    /**
     * 删除文件
     * @param fileName
     */
    void deleteFile(String fileName);
}
