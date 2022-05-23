package com.ms.blogserver.model.bo;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/10
 */
@Data
public class FileSimpleBO {
    private String fileName;
    private Date time;
}
