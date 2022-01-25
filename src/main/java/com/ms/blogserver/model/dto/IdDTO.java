package com.ms.blogserver.model.dto;

import com.ms.blogserver.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IdDTO extends BaseDTO implements Serializable {
    private Long id;
    private List<Long> idList;

    public IdDTO(){}

    public IdDTO(Long id){
        this.id = id;
    }

    public IdDTO(Long id, List<Long> idList){
        this.idList = idList;
        this.id = id;
    }
}
