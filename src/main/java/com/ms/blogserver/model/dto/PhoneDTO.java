package com.ms.blogserver.model.dto;

import com.ms.blogserver.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PhoneDTO extends BaseDTO {
    private String phone;
}
