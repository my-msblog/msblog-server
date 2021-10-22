package com.ms.blogserver.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/10/22
 */
@ToString
@Data
public class StatusDTO {
    private Long id;
    private String username;
    private Integer status;
}
