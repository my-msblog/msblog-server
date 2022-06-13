package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/20
 */
@Data
@TableName(value = "ms_logs")
public class Logs implements Serializable {
    private static final long serialVersionUID = -1087397094673045830L;
    @TableId
    private Long id;
    private String fileName;
    private LocalDateTime time;

    public Logs(){};
    public Logs(String fileName, LocalDateTime time){
        this.fileName = fileName;
        this.time = time;
    }
}
