package com.yberdaliyev.controllers.filters.unused;

import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * Created by Yerlan on 28.02.2017.
 */

//public class RegFilter implements Filter {
//    private static Logger logger = Logger.getLogger(RegFilter.class);
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        logger.trace("on Registration filter");
//        String form_button = req.getParameter("reg_button");
//        req.setAttribute("unfilled","hidden");
//
//        if (form_button!=null) {
//            logger.trace(form_button);
//            if (checkParams(req)) {
//                logger.trace("regfilter: correct input");
//                chain.doFilter(req, resp);
//            } else {
//                logger.trace("regfilter: incorrect input in fields");
//                req.setAttribute("unfilled","visible");
//                req.getRequestDispatcher("/registration.jsp").forward(req,resp);
//            }
//        } else {
//            chain.doFilter(req,resp);
//        }
//
//    }
//
//    public void init(FilterConfig config) throws ServletException {}
//
//    private static boolean checkParams(ServletRequest request) {
//        boolean isOk = true;
//
//        if (request.getParameter("user_name").trim().isEmpty()) isOk=false;
//        if (request.getParameter("user_surname").trim().isEmpty()) isOk=false;
//        if (request.getParameter("user_login").trim().isEmpty()) isOk=false;
//        if (request.getParameter("user_password").trim().isEmpty()) isOk=false;
//        if (request.getParameter("user_email").trim().isEmpty()) isOk=false;
//        String role = request.getParameter("user_role");
//        String spec_pwd = request.getParameter("account_password");
//        if (role.equals("driver")) {
//            if (!spec_pwd.equals("driver")) isOk=false;
//        }
//        if (role.equals("admin")) {
//            if (!spec_pwd.equals("admin")) isOk=false;
//        }
//        return isOk;
//    }

//}
