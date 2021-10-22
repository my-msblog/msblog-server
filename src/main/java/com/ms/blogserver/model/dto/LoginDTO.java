package com.ms.blogserver.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class LoginDTO extends BaseDTO {

    private String username;
    private String password;
    private String key;
    private String code;
}
