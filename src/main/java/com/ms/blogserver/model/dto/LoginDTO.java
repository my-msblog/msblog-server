package com.ms.blogserver.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
@Data
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = -4332592631543291804L;
    private String username;
    private String password;
    private String key;
    private String code;
}
