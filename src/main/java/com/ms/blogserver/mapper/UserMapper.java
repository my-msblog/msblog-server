package com.ms.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ms.blogserver.model.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     *  永久删除
     * @param id
     */
    @Delete("delete from ms_user where id = #{id}")
    void deletedByDel(@Param("id") Long id);

    /**
     * 查询所有数据，包括delete为1
     * @return
     */
    @Select("select * from ms_user")
    List<User> selectAll();
}
