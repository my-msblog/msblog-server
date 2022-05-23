package com.ms.blogserver.model.dto;

import lombok.Data;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
@Data
public class LoginDTO{

    private String username;
    private String password;
    private String key;
    private String code;
}
