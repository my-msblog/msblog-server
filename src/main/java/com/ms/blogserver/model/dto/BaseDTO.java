package com.ms.blogserver.model.dto;

import com.ms.blogserver.constant.contexts.DigitalContexts;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@ToString
public class BaseDTO implements Serializable {

    private Integer page;
    private Integer size;

    public Integer getPage() {
        if (page <= DigitalContexts.ZERO){
            page = DigitalContexts.ONE;
        }
        return page;
    }

    public void setPage(Integer page) {
       this.page = page;
    }

    public Integer getSize() {
        if (size <= DigitalContexts.ZERO){
            size = DigitalContexts.FIVE;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    public BaseDTO(){}

    public BaseDTO(Integer size, Integer page){
        if (size > 0 && page >0){
            this.size = size;
            this.page = page;
        }else {
            this.size = DigitalContexts.ONE;
            this.page = DigitalContexts.FIVE;
        }
    }
}
