package com.ms.blogserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.constant.LoginContexts;
import com.ms.blogserver.constant.exception.CustomException;
import com.ms.blogserver.converter.UserConverter;
import com.ms.blogserver.entity.vo.PageVO;
import com.ms.blogserver.entity.vo.UserVO;
import com.ms.blogserver.utils.EncryptPassword;
import com.ms.blogserver.entity.User;
import com.ms.blogserver.mapper.UserMapper;
import com.ms.blogserver.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


    @Override
    public boolean hasUserName(String username) {
        return findByUserName(username) == null;
    }

    @Override
    public User getUserByID(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void insertUser(User user) {
        user.setPwd(EncryptPassword.encrypt(user.getPwd()));
        baseMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        if (user == null){
            throw new CustomException(LoginContexts.AUTHENTIC_FAIL);
        }else if (user.getId() == null){
            throw new CustomException(LoginContexts.ID_IS_NULL);
        }
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
        wrapper.eq("username",userName);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public String getPassword(String username) {
        return findByUserName(username).getPwd();
    }

    @Override
    public PageVO<UserVO> getPage() {
        IPage<User> iPage = new Page<>(1,5);
        baseMapper.selectPage(iPage,null);
        List<UserVO> voList = UserConverter.INSTANCE.toListData(iPage.getRecords());
        PageVO<UserVO> pageVO = new PageVO<>(iPage.getCurrent(),iPage.getSize(),iPage.getTotal());
        pageVO.setList(voList);
        return pageVO;
    }
}
