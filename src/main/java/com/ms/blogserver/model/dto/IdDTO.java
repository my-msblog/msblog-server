package com.ms.blogserver.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class IdDTO {
    private Long id;
    private List<Long> idList;
}
