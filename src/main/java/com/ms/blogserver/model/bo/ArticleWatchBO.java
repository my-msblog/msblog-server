package com.ms.blogserver.model.bo;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @description: 文章观看数缓存bo类
 * @author: zhh
 * @time: 2022/3/24
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleWatchBO {
    private Long articleId;
    private LocalDateTime time;
}
