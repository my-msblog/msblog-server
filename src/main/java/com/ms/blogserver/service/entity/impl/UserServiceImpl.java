package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.constant.contexts.RoleContexts;
import com.ms.blogserver.converter.dto.UserDTOConverter;
import com.ms.blogserver.converter.vo.UserVOConverter;
import com.ms.blogserver.exception.ProgramException;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.dto.UserDTO;
import com.ms.blogserver.model.entity.UserRole;
import com.ms.blogserver.service.entity.UserRoleService;
import com.ms.blogserver.utils.RegularUtils;
import com.ms.blogserver.model.vo.UserVO;
import com.ms.blogserver.utils.EncryptPassword;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.mapper.UserMapper;
import com.ms.blogserver.service.entity.UserService;
import com.ms.blogserver.utils.PageInfoUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public boolean hasUserName(String username) {
        return findByUserName(username) == null;
    }

    @Override
    public User getUserByID(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void insertUser(UserDTO userDTO) {
        userDTO.setPwd(EncryptPassword.encrypt(userDTO.getPwd()));
        User user = UserDTOConverter.INSTANCE.fromData(userDTO);
        String email = user.getEmail();
        if (StringUtils.isNotEmpty(email)) {
            if (!RegularUtils.isEmail(email)) {
                throw new CustomException(LoginContexts.EMAIL_ERROR);
            }
        }
        baseMapper.insert(user);
        // 新用户添加权限，默认权限为2
        User newUser = findByUserName(user.getUsername());
        userRoleService.save(new UserRole(newUser.getId(), RoleContexts.CONTENT_MANAGER_ID));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (userDTO == null || userDTO.getId() == 0) {
            throw new CustomException("UserService-updateUser:" + LoginContexts.AUTHENTIC_FAIL);
        }
        User user = getUserByID(userDTO.getId());
        UserDTOConverter.INSTANCE.fromDataNoNull(userDTO, user);
        baseMapper.updateById(user);
    }

    @Override
    public List<User> findAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public int removeById(Long id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int deleteById(Long id) {
        return baseMapper.deletedByDel(id);
    }

    @Override
    public User findByUserName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public String getPassword(String username) {
        User byUserName = findByUserName(username);
        if (byUserName == null) {
            throw new CustomException(LoginContexts.USER_IS_NOT_EXIST);
        }
        try {
            return byUserName.getPwd();
        } catch (Exception e) {
            throw new ProgramException(e.getMessage());
        }
    }

    @Override
    public PageInfo<UserVO> getPage(BaseDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        List<User> userList = findAll();
        List<UserVO> voList = UserVOConverter.INSTANCE.toDataList(userList);
        PageInfo<UserVO> userVOPageInfo = new PageInfo<>();
        PageInfoUtil.transform(new PageInfo<>(userList), userVOPageInfo);
        userVOPageInfo.setList(voList);
        return userVOPageInfo;
    }
}
