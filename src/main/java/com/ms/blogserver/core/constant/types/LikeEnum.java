package com.ms.blogserver.core.constant.types;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/25
 */
public enum LikeEnum {
    /**
     * 已点赞
     */
    LIKE(0, "点赞"),

    /**
     * 未点赞/取消点赞
     */
    UNLIKE(1, "未点赞/取消点赞");

    public Integer status;
    public String message;

    LikeEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
