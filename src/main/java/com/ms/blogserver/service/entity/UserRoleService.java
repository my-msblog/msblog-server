package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.model.entity.UserRole;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
public interface UserRoleService extends IService<UserRole> {

    Long getRidByUid(Long uid) throws CustomException;


}
