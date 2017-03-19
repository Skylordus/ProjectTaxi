package com.yberdaliyev.controllers.filters.unused;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Yerlan on 28.02.2017.
 */

public class ClientInputsInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = Logger.getLogger(ClientInputsInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.trace("on Registration Interceptor");
        boolean ok = true;
        if ( request.getParameter("from").isEmpty() ) ok=false;
        if ( request.getParameter("to").isEmpty() ) ok=false;
        if ( request.getParameter("pickup_time").isEmpty() ) ok=false;
        if ( request.getParameter("plan").isEmpty() ) ok=false;
        if (!ok) {
            request.setAttribute("notfilled",true);

        }

        return ok;

    }




}
