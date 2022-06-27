package com.ms.blogserver.service.api;

import com.ms.blogserver.core.base.BaseService;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/12
 */
public interface UserOperationService extends BaseService {
    /**
     * 批量删除用户
     * @param idList
     */
    void deleteUserListId(List<Long> idList);
}
