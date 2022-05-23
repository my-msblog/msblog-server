package com.ms.blogserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/31
 */
@Data
public class UserVO implements BaseVO {
    private Long id ;
    private String username;
    private String phone;
    private String email;
    private String token;
    private String introduction;
    private Integer sex;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
