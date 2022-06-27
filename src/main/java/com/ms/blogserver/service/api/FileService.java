package com.ms.blogserver.service.api;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.core.base.BaseService;
import com.ms.blogserver.model.vo.FileVO;
import com.ms.blogserver.model.vo.LogVO;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/10
 */
public interface FileService extends BaseService {

    /**
     * 日志文件分页
     *
     * @param dto dto
     * @return {@link PageInfo}<{@link FileVO}>
     */
    PageInfo<FileVO> findFileName(BaseDTO dto);

    /**
     * 文件名获取日志内容
     *
     * @param fileName 文件名称
     * @return {@link LogVO}
     */
    LogVO getLog(String fileName);

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     */
    void deleteFile(String fileName);
}
