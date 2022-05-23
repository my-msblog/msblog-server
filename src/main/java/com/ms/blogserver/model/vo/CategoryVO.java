package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;

/**
 * @description:
 * @author: zhh
 * @time: 2021/12/15
 */
@Data
public class CategoryVO implements BaseVO {
    private Integer categoryId;
    private String category;
    private Integer count;
}
