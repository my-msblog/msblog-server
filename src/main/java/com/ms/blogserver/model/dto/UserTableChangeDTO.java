package com.ms.blogserver.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/7/1
 */
@Data
public class UserTableChangeDTO implements Serializable {
    private static final long serialVersionUID = 8171209167250546877L;
    private Long id;
    private String username;
    private String pwd;
    private String phone;
    private String email;
    private String introduction;
    private Integer sex;
    /**
     * 手机验证码
     */
    private String code;
    /**
     * 角色id
     */
    private Long roleId;
}
