package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.entity.User;

import java.util.List;
/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */

public interface UserService extends IService<User> {
    /**
     *  判断用户名是否存在
     * @param username
     */
    void hasUserName(String username);
    /**
     *  通过id获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);
    /**
     * 插入用户
     * @param dto
     */
    void insertUser(UserTableChangeDTO dto);
    /**
     * 修改用户
     * @param dto
     */
    void updateUser(UserTableChangeDTO dto);
    /**
     * 查询所有用户
     * @return
     */
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
     */
    void deleteById(Long id);

    /**
     * 根据用户名查找
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 获取密码
     * @param username
     * @return
     */
    String getPassword(String username);

}
