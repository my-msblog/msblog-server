package com.ms.blogserver.model.dto;

import lombok.Data;

@Data
public class UserTableChangeDTO {
    private String username;
    private String pwd;
    private String phone;
    private String email;
    private String introduction;
    private int sex;
    private String code;
}
