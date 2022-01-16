package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/15
 */
@Data
@ToString
@Builder
public class RequestItemVO implements BaseVO {

    /**
     * 控制器名称
     */
    private String controllerName;
    /**
     * 请求方法名
     */
    private String methodName;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 请求路径
     */
    private String requestUrl;
    /**
     * 方法说明
     */
    private String annotationValue;
    /**
     * 请求参数
     */
    private Class<?>[] methodParmaTypes;
    /**
     *  result name
     */
    private String returnName;
    /**
     * vo参数
     */
    private Map<String, Object> returnValueMap;

    public RequestItemVO(){}

    public RequestItemVO(String requestUrl, String requestType, String controllerName,
                         String requestMethodName, String annotationValue, Class<?>[] methodParmaTypes,
                         Map<String, Object> returnValueMap, String returnName){
        this.requestUrl = requestUrl;
        this.requestType = requestType;
        this.controllerName = controllerName;
        this.methodName = requestMethodName;
        this.methodParmaTypes = methodParmaTypes;
        this.returnValueMap = returnValueMap;
        this.returnName = returnName;
        this.annotationValue = annotationValue;
    }
}
