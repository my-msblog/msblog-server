package com.ms.blogserver.model.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/10/22
 */
@ToString
@Data
public class StatusDTO implements Serializable {
    private Long id;
    private String username;
    private Integer status;
}
