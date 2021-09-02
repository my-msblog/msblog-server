package com.ms.blogserver.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/7/1
 */
@Data
@ToString
public class UserTableChangeDTO {
    private Long id;
    private String username;
    private String pwd;
    private String phone;
    private String email;
    private String introduction;
    private Integer sex;
    private String code;
}
