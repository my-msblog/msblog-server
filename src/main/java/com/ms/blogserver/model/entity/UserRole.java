package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
@Data
@TableName(value = "ms_user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 456882804863484152L;
    @TableId
    private Long id;
    private Long uid;
    private Long rid;

    public UserRole(Long uid, Long rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public UserRole() {
    }
}
