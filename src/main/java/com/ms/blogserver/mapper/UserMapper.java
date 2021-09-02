package com.ms.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ms.blogserver.model.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     *  永久删除
     * @param id
     * @return
     */
    @Delete("delete from ms_user where id = #{id}")
    int deletedByDel(@Param("id") Long id);

}
