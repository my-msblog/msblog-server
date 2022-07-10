package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@TableName(value = "ms_article_tag")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = -1511422415409850329L;
    @TableId
    private Long id;
    private Long articleId;
    private Long tagId;


}
