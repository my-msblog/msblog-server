package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
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
public class RequestItemVO implements BaseVO {

    /**
     * 控制器名称
     */
    public String controllerName;
    /**
     * 请求方法名
     */
    public String methodName;
    /**
     * 请求类型
     */
    public String requestType;
    /**
     * 请求路径
     */
    public String requestUrl;
    /**
     * 请求参数
     */
    public Class<?>[] methodParmaTypes;
    /**
     *  result name
     */
    public String returnName;
    /**
     * vo参数
     */
    public Map<String, Object> returnValueMap;

    public RequestItemVO(String requestUrl, String requestType,
                         String controllerName, String requestMethodName,
                         Class<?>[] methodParmaTypes, Map<String, Object> returnValueMap,
                         String returnName){
        this.requestUrl = requestUrl;
        this.requestType = requestType;
        this.controllerName = controllerName;
        this.methodName = requestMethodName;
        this.methodParmaTypes = methodParmaTypes;
        this.returnValueMap = returnValueMap;
        this.returnName = returnName;
    }
}
