package com.ms.blogserver.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
public class IdDTO {
    private Long id;
    private List<Long> idList;
}
