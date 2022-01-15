package com.ms.blogserver.service.api.impl;

import com.ms.blogserver.core.base.BaseVO;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.model.vo.RequestItemVO;
import com.ms.blogserver.service.api.SystemService;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.*;
import java.util.*;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/15
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Override
    public List<RequestItemVO> getAllUri(RequestMappingHandlerMapping requestMappingHandlerMapping) throws Exception {
        List<RequestItemVO> requestItemVOList = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry
                : handlerMethods.entrySet()) {
            RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
            RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();
            String requestType = methodCondition.getMethods().toString();
            assert patternsCondition != null;
            String requestUrl = patternsCondition.getPatterns().iterator().next();
            String controllerName = mappingInfoValue.getBeanType().toString();
            // 过滤api接口
            if ("class org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController"
                    .equals(controllerName)) {
                continue;
            }
            if (controllerName.contains("swagger") || controllerName.contains("core")) {
                continue;
            }
            String requestMethodName = mappingInfoValue.getMethod().getName();
            Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
            Type returnType = mappingInfoValue.getMethod().getGenericReturnType();
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments=type.getActualTypeArguments();

            for(Type typeArgument:typeArguments) {
                Class typeArgClass = (Class) typeArgument;

                System.out.println("泛型类型：" + typeArgClass);
            }
            //Class<?> floodObjectClass = Class.forName(floodStr);
            //Map<String, Object> returnValueMap = this.classToJson(floodObjectClass, true);
            RequestItemVO item = new RequestItemVO(requestUrl, requestType, controllerName,
                    requestMethodName, methodParamTypes, null);
            requestItemVOList.add(item);
        }
        return requestItemVOList;
    }


    @Override
    public Map<String, Object> classToJson(Class<?> clazz, Boolean listKey) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> result = new HashMap<String, Object>(12);
        List<Field> fieldList = Arrays.asList(fields);
        fieldList.forEach(field -> {
            String fieldName = field.getName();
            Class<?> type = field.getType();
            if (BaseVO.class.isAssignableFrom(type)) {
                result.put(fieldName, classToJson(type, listKey));
            } else if (type.equals(List.class)) {
                Type genericClass = field.getGenericType();
                ParameterizedType pt = (ParameterizedType) genericClass;
                Class<?> actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
                if (BaseVO.class.isAssignableFrom(actualTypeArgument)) {
                    if (listKey) {
                        result.put(fieldName,
                                new ArrayList<>(Collections.singletonList(classToJson(actualTypeArgument, false))));
                    } else {
                        result.put(fieldName, "This object itself");
                    }
                } else {
                    result.put(fieldName, actualTypeArgument.getTypeName());
                }
            } else {
                result.put(fieldName, type.getTypeName());
            }
        });
        return result;
    }
}
