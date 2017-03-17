//package com.yberdaliyev.controllers.filters;
//
//import org.apache.log4j.Logger;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
///**
// * Created by Yerlan on 28.02.2017.
// */
//
//public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
//    private static Logger logger = Logger.getLogger(AuthenticationInterceptor.class);
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        logger.trace("on auth filter");
//        ArrayList<String> urlList = new ArrayList<>();
//        urlList.add("/user_account");
//        urlList.add("/admin_account");
//        urlList.add("/driver_account");
//
//        String path = request.getServletPath();
//        logger.trace(path);
//
//        if (urlList.contains(path)) {
//            HttpSession session = request.getSession(false);
//            Object user_role = null;
//            if (session!=null) {
//                user_role = session.getAttribute("user_role");
//            }
//            logger.trace("session ="+user_role);
//
//            if ((user_role==null)||(session==null)) {
//                logger.trace("session object null -> going to redirect");
//                response.sendRedirect("/");
//            } else
//            if ((int)user_role==1) {
//                if ( path.equals("/admin_account")||path.equals("/driver_account") ) {
//                    response.sendRedirect("user_account");
//                } else return true;
//            } else
//            if ((int)user_role==2) {
//                if ( path.equals("/admin_account")||path.equals("/user_account") ) {
//                    response.sendRedirect("driver_account");
//                } else return true;
//            } else
//            if ((int)user_role==3) {
//                if ( path.equals("/driver_account")||path.equals("/user_account") ) {
//                    response.sendRedirect("admin_account");
//                } else return true;
//            }
//        } else {
//            return true;
//        }
//        return false;
//    }
//
//
//}
