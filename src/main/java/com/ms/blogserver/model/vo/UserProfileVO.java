package com.ms.blogserver.model.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/8
 */
@ToString
@Data
public class UserProfileVO {
    private Long id;
    private String username;
    private Integer sex;
    private String email;
    private String phone;
    private String role;
    private Integer deleted;

}
