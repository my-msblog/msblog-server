package com.ms.blogserver.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileSimpleBO {
    private String fileName;
    private Date time;
}
