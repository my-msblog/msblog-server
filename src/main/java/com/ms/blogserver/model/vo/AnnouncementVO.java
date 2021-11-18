package com.ms.blogserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
}
