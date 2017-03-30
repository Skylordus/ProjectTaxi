package com.yberdaliyev.controllers.filters;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yerlan on 16.03.2017.
 */
@Component
public class AccessDeniedRedirectionHandler implements AccessDeniedHandler {
    private static Logger logger = Logger.getLogger(AccessDeniedRedirectionHandler.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        if (request.isUserInRole("ROLE_ADMIN")) {response.sendRedirect("/admin_account");}
        else if (request.isUserInRole("ROLE_DRIVER")) {response.sendRedirect("/driver_account");}
        else if (request.isUserInRole("ROLE_CLIENT")) {response.sendRedirect("/client_account"); }
        else response.sendRedirect("/");


    }
}

