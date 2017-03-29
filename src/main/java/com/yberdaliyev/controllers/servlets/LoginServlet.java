package com.yberdaliyev.controllers.servlets;

import com.sun.security.auth.UserPrincipal;
import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.User;
import com.yberdaliyev.services.IUserService;
import com.yberdaliyev.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by Yerlan on 25.02.2017.
 */
@Controller
public class LoginServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpSession session)  {
        logger.warn("login controller, login method start");
        String error = request.getParameter("error");
        if ((error!=null)&&(error.equals("true"))) {return new ModelAndView("index");}
        ModelAndView modelAndView = new ModelAndView("failed");

        String login = request.getUserPrincipal().getName();

        USER_ROLES role = null;
        if (request.isUserInRole("ROLE_ADMIN")) role = USER_ROLES.ROLE_ADMIN;
        if (request.isUserInRole("ROLE_DRIVER")) role = USER_ROLES.ROLE_DRIVER;
        if (request.isUserInRole("ROLE_CLIENT")) role = USER_ROLES.ROLE_CLIENT;
        if (role == null) {modelAndView.addObject("cause","no role"); return modelAndView;}

        User user = userService.getUserByLoginAndRole(login,role);

        if (user==null) {modelAndView.addObject("cause","could not find user"); return modelAndView;}

        session.setAttribute("user_object", user);
        modelAndView.setViewName("index");

        return modelAndView;
    }

    @GetMapping("/")
    public String showIndex( )  {
        return "index";
    }

}
