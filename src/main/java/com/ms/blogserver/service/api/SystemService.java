package com.ms.blogserver.service.api;

import com.ms.blogserver.core.base.BaseService;
import com.ms.blogserver.model.vo.RequestItemVO;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/15
 */
public interface SystemService extends BaseService {
    /**
     * 获取所有接口
     *
     * @param requestMappingHandlerMapping 请求映射处理程序映射
     * @return {@link List}<{@link RequestItemVO}>
     * @throws Exception 异常
     */
    List<RequestItemVO> getAllUrl(RequestMappingHandlerMapping requestMappingHandlerMapping) throws Exception;

    /**
     * vo类装json
     *
     * @param clazz   clazz
     * @param listKey 列表关键
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> classToJson(Class<?> clazz, Boolean listKey);
}
