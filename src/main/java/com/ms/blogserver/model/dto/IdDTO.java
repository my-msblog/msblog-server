package com.ms.blogserver.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
public class IdDTO implements Serializable {
    private Long id;
    private List<Long> idList;
}
