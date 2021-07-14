package com.ms.blogserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: zhh
 * @time: 2021/7/14
 */
@Component
@Slf4j
public class LogInterceptorAdapter extends DispatcherServlet {

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("\033[2;36m"+"请求方法:"+request.getMethod()+", 请求路径:["+request.getRequestURI()+"]"+" \033[0m");
        super.doDispatch(request, response);
    }
}
