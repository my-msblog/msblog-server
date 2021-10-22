package com.ms.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ms.blogserver.model.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Mapper
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

    /**
     * 查询用户，deleted为1
     * @param id
     * @return
     */
    @Select("select * from ms_user where id = #{id}")
    User selectUser(@Param("id") Long id);

    /**
     * 修改指定id的deleted
     * @param id
     * @param deleted
     */
    @Update("update ms_user set deleted = #{deleted} where id = #{id} ")
    void updateUserStatus(@Param("id") Long id, @Param("deleted") int deleted);
}
