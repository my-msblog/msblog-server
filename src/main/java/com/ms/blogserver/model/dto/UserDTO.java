package com.ms.blogserver.model.dto;

import com.ms.blogserver.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserDTO extends BaseDTO {
    private Long id;
    private String username;
    private String pwd;
    private String phone;
    private String email;
    private String introduction;
    private Integer sex;
}
