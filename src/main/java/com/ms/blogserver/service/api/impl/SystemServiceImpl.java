package com.ms.blogserver.service.api.impl;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.base.BaseVO;
import com.ms.blogserver.core.exception.ProgramException;
import com.ms.blogserver.model.vo.RequestItemVO;
import com.ms.blogserver.service.api.SystemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.*;
import java.util.*;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/15
 */
@Service
public class SystemServiceImpl implements SystemService {

    private static final String LANG = "lang";

    @Override
    public List<RequestItemVO> getAllUrl(RequestMappingHandlerMapping requestMappingHandlerMapping) throws Exception {
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
                    .equals(controllerName) || controllerName.contains("swagger")
                    || controllerName.contains("core")) {
                continue;
            }
            String requestMethodName = mappingInfoValue.getMethod().getName();
            Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
            Method method = mappingInfoValue.getMethod();
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            String annotationValue = Objects.isNull(apiOperation) ? "" : apiOperation.value();
            ResolvableType resolvableType = ResolvableType.forMethodReturnType(method);
            Type type = resolvableType.getType();
            String returnName = type.getTypeName();
            Map<String, Object> returnValueMap = new HashMap<>(12);
            genericClass(type, returnValueMap);
            RequestItemVO item = RequestItemVO.builder().controllerName(controllerName)
                    .methodName(requestMethodName).requestUrl(requestUrl).requestType(requestType)
                    .annotationValue(annotationValue).methodParmaTypes(methodParamTypes).returnName(returnName)
                    .returnValueMap(returnValueMap)
                    .build();
            requestItemVOList.add(item);
        }
        return requestItemVOList;
    }
    @Override
    public Map<String, Object> classToJson(Class<?> clazz, Boolean listKey) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> result = new HashMap<>(12);
        List<Field> fieldList = Arrays.asList(fields);
        fieldList.forEach(field -> {
            String fieldName = field.getName();
            Class<?> type = field.getType();
            if (BaseVO.class.isAssignableFrom(type)) {
                result.put(fieldName, classToJson(type, listKey));
            } else if (type.equals(List.class)) {
                genericVoTypes(result, field, listKey);
            } else if (type.equals(PageInfo.class)) {
                Map<String, Object> pageInfoMap = new HashMap<>(12);
                genericVoTypes(pageInfoMap,field,listKey);
                result.put(type.getTypeName(), pageInfoMap);
            } else {
                result.put(fieldName, type.getTypeName());
            }
        });
        return result;
    }

    private Class<?> genericClass(Type type, Map<String, Object> returnValueMap) {
        Class<?> genericClass;
        Map<String, Object> childrenMap = new HashMap<>(12);
        if (type instanceof ParameterizedType) {
            ParameterizedTypeImpl pt = (ParameterizedTypeImpl) type;
            Type[] types = pt.getActualTypeArguments();
            Type typeArgument = types[0];
            if (typeArgument instanceof ParameterizedType) {
                genericClass = genericClass(typeArgument, childrenMap);
                returnValueMap.put(typeArgument.getTypeName(), childrenMap);
            } else {
                genericClass = (Class<?>) types[0];
                String typeName = genericClass.getTypeName();
                if(typeName.contains(LANG)) {
                    returnValueMap.put(typeName, typeName);
                }else {
                    returnValueMap.put(typeName, this.classToJson(genericClass, true));
                }
            }
        } else {
           throw new ProgramException("class cast error: not cast to class");
        }
        return genericClass;
    }
    private void genericVoTypes(Map<String, Object> map, Field field, Boolean listKey){
        Type genericClass = field.getGenericType();
        String fieldName = field.getName();
        ParameterizedType pt = (ParameterizedType) genericClass;
        Class<?> actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
        if (BaseVO.class.isAssignableFrom(actualTypeArgument)) {
            if (listKey) {
                map.put(fieldName,
                        new ArrayList<>(Collections.singletonList(classToJson(actualTypeArgument, false))));
            } else {
                map.put(fieldName, "This object itself");
            }
        } else {
            map.put(fieldName, actualTypeArgument.getTypeName());
        }
    }
}
