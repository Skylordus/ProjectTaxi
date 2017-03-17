package com.yberdaliyev.controllers.filters;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Yerlan on 28.02.2017.
 */

public class RegInputsInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = Logger.getLogger(RegInputsInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.trace("on Registration Interceptor");
        String form_button = request.getParameter("reg_button");
        request.setAttribute("unfilled","hidden");

        if (form_button!=null) {
            logger.trace(form_button);
            if (checkParams(request)) {
                logger.trace("regfilter: correct input");
                return true;
            } else {
                logger.trace("regfilter: incorrect input in fields");
                request.setAttribute("unfilled","visible");
                request.getRequestDispatcher("/registration.jsp").forward(request,response);
            }
        } else {
            return true;
        }
        return false;
    }

    private static boolean checkParams(ServletRequest request) {
        boolean isOk = true;

        if (request.getParameter("user_name").trim().isEmpty()) isOk=false;
        if (request.getParameter("user_surname").trim().isEmpty()) isOk=false;
        if (request.getParameter("user_login").trim().isEmpty()) isOk=false;
        if (request.getParameter("user_password").trim().isEmpty()) isOk=false;
        if (request.getParameter("user_email").trim().isEmpty()) isOk=false;
        String role = request.getParameter("user_role");
        String spec_pwd = request.getParameter("account_password");
        if (role.equals("driver")) {
            if (!spec_pwd.equals("driver")) isOk=false;
        }
        if (role.equals("admin")) {
            if (!spec_pwd.equals("admin")) isOk=false;
        }
        return isOk;
    }


}
