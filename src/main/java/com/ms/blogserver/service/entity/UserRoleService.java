package com.ms.blogserver.service.entity;

import com.ms.blogserver.core.base.EntityService;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.mapper.UserRoleMapper;
import com.ms.blogserver.model.entity.UserRole;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
public interface UserRoleService extends EntityService<UserRole, UserRoleMapper> {

    /**
     * 根据用户id获取角色id
     *
     * @param uid uid
     * @return {@link Long}
     * @throws CustomException 自定义异常
     */
    Long getRidByUid(Long uid) throws CustomException;


}
