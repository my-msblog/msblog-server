package com.ms.blogserver.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/10/22
 */
@Data
public class StatusDTO implements Serializable {
    private Long id;
    private String username;
    private Integer status;
}
