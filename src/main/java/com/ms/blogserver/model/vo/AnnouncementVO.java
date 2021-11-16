package com.ms.blogserver.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/16
 */
@Data
@ToString
public class AnnouncementVO {
    private String announcement;
    private String user;
    private Date time;
}
