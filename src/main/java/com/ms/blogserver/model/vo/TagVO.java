package com.ms.blogserver.model.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@ToString
public class TagVO {
    private Long tageId;
    private String name;
    private String nameZh;
}
