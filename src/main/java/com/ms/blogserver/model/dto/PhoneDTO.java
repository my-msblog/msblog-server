package com.ms.blogserver.model.dto;

import com.ms.blogserver.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PhoneDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 6462880228083813162L;
    private String phone;
}
