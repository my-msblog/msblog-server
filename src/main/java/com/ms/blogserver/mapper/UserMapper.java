package com.ms.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ms.blogserver.model.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;


public interface UserMapper extends BaseMapper<User> {

    @Delete("delete from ms_user where id = #{id}")
    int deletedByDel(@Param("id") Long id);

}
