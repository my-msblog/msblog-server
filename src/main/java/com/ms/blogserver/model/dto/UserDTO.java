package com.ms.blogserver.model.dto;

import com.ms.blogserver.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -2518369316766231780L;
    private Long id;
    private String username;
    private String pwd;
    private String phone;
    private String email;
    private String introduction;
    private Integer sex;
}
