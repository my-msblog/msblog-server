package com.ms.blogserver.service.api;

import com.ms.blogserver.model.vo.RequestItemVO;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/15
 */
public interface SystemService {
    /**
     * 获取所有接口
     * @param requestMappingHandlerMapping
     * @return
     */
    List<RequestItemVO> getAllUri(RequestMappingHandlerMapping requestMappingHandlerMapping) throws Exception;

    /**
     * vo类装json
     * @param clazz
     * @param listKey
     * @return
     */
    Map<String, Object> classToJson(Class<?> clazz, Boolean listKey);
}
