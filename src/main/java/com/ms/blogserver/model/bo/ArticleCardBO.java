package com.ms.blogserver.model.bo;

import com.ms.blogserver.model.vo.TagVO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@Data
@ToString
public class ArticleCardBO implements Serializable {
    private Long id;
    private String title;
    private String content;
    private String cover;
    private Integer type;
    private String typeName;
    private List<TagVO> tagVOList;
}
