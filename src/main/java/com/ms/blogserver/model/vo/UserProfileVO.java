package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/8
 */
@Data
public class UserProfileVO implements BaseVO {
    private Long id;
    private String username;
    private Integer sex;
    private String email;
    private String phone;
    private String role;
    private Integer deleted;

}
