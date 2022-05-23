package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
public class TagVO implements BaseVO {
    private Long tagId;
    private String name;
    private String nameZh;
}
