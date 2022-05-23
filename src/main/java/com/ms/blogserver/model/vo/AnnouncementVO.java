package com.ms.blogserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/16
 */
@Data
public class AnnouncementVO implements BaseVO {
    private String announcement;
    private String user;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
}
