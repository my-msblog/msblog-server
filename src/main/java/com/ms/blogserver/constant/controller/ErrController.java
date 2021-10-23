package com.ms.blogserver.constant.controller;

import com.ms.blogserver.constant.result.ResultString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 错误页面
 * @author: z
 * @time: 2021/5/14
 */
@RestController
@Slf4j
public class ErrController extends BasicErrorController {


    public ErrController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        getErrorProperties().setIncludeException(true);
        getErrorProperties().setIncludeMessage(ErrorProperties.IncludeAttribute.ALWAYS);
        getErrorProperties().setIncludeStacktrace(ErrorProperties.IncludeAttribute.ALWAYS);
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        Map<String, Object> map = new HashMap<String, Object>(12);
        map.put("code", body.get("status"));
        switch (status.value()){
            case 404:
                map.put("msg", ResultString.PAGE_NO_FOUND.data);
                map.put("data", "");
                break;
            case 401:
                map.put("msg", ResultString.NO_AUTHORIZED.data);
                map.put("data", "");
                break;
            default:
                map.put("msg","unknown");
                map.put("data", "");
                break;
        }
        return new ResponseEntity<>(map, status);
    }

}
