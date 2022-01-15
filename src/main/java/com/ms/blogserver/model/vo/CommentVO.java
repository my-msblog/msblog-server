package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@Data
@ToString
public class CommentVO implements BaseVO {
    private Long id;
    private String content;
    private String commenter;
    private String respondent;
    private Integer likes;
    private Long parentId;
    private LocalTime createTime;

    private List<CommentVO> children;
}
