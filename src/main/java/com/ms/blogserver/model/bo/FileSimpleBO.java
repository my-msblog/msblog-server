package com.ms.blogserver.model.bo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/10
 */
@Data
@ToString
public class FileSimpleBO {
    private String fileName;
    private Date time;
}
