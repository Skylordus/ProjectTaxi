package com.yberdaliyev.controllers.filters.unused;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Yerlan on 28.02.2017.
 */
//
//public class AuthFilter implements Filter {
//    private static Logger logger = Logger.getLogger(AuthFilter.class);
//    private ArrayList<String> urlList;
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        logger.trace("on auth filter");
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) resp;
//        String path = request.getServletPath();
//        logger.trace(path);
//        if (urlList.contains(path)) {
//            HttpSession session = request.getSession(false);
//            Object user_role = session.getAttribute("user_role");
//            logger.trace("session ="+user_role);
//
//            if (user_role==null) {
//                logger.trace("session object null -> going to redirect");
//                response.sendRedirect("/index.jsp");
//            } else
//            if ((int)user_role==1) {
//                if ( path.equals("/admin_account")||path.equals("/driver_account") ) {
//                    response.sendRedirect("/client_account.jsp");
//                } else chain.doFilter(req, resp);
//            } else
//            if ((int)user_role==2) {
//                if ( path.equals("/admin_account")||path.equals("/user_account") ) {
//                    response.sendRedirect("/driver_account.jsp");
//                } else chain.doFilter(req, resp);
//            } else
//            if ((int)user_role==3) {
//                if ( path.equals("/driver_account")||path.equals("/user_account") ) {
//                    response.sendRedirect("/admin_account.jsp");
//                } else chain.doFilter(req, resp);
//            }
//        } else {
//            chain.doFilter(req, resp);
//        }
//
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//        String str = config.getInitParameter("avoid-urls");
//        StringTokenizer st = new StringTokenizer(str,",");
//        urlList = new ArrayList<>();
//        while (st.hasMoreTokens()) {
//            urlList.add(st.nextToken());
//        }
//
//    }
//
//}
