package com.ms.blogserver.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserTableChangeDTO {
    private Long id;
    private String username;
    private String pwd;
    private String phone;
    private String email;
    private String introduction;
    private int sex;
    private String code;
}
