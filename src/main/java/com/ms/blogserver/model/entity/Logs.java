package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/20
 */
@Data
@ToString
@TableName(value = "ms_logs")
public class Logs implements Serializable {
    private Long id;
    private String fileName;
    private LocalDateTime time;

    public Logs(){};
    public Logs(String fileName, LocalDateTime time){
        this.fileName = fileName;
        this.time = time;
    }
}
