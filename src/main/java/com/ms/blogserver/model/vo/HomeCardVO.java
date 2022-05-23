package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/5
 */
@Data
public class HomeCardVO implements BaseVO {
    private Integer article;
    private Integer tag;
    private Integer category;
}
