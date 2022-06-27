package com.ms.blogserver.core.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2022/3/13
 */
@Data
@ToString
@AllArgsConstructor
public class BaseOptions <R, T> {
    private R value;
    private T label;

    public BaseOptions() {
    }
}
