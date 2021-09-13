package com.ms.blogserver.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ms.blogserver.constant.contexts.ErrorContexts;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.service.api.UserOperationService;
import com.ms.blogserver.service.entity.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/12
 */
@Service
@Slf4j
public class UserOperationServiceImpl implements UserOperationService {

    @Autowired
    private UserService userService;

    @Override
    public void deleteUserListId(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)){
            throw new CustomException(ErrorContexts.PARAMETER_LIST_IS_EMPTY);
        }
        userService.remove(new LambdaQueryWrapper<User>().in(User::getId,idList));
    }
}
