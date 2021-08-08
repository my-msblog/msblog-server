package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.vo.UserVO;

import java.util.List;

public interface UserService extends IService<User> {

    void hasUserName(String username);

    User getUserByID(Long id);

    void insertUser(UserTableChangeDTO dto);

    void updateUser(UserTableChangeDTO dto);

    List<User> findAll();

    /**
     * 删除用户
     * 逻辑删除
     * deleted默认0，删除时为1
     *
     * @param id
     * @return
     */
    int removeById(Long id);

    /**
     * 物理删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    User findByUserName(String userName);

    String getPassword(String username);

    PageInfo<UserVO> getPage(BaseDTO dto);
}
