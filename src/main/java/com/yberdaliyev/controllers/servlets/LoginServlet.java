package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.User;
import com.yberdaliyev.services.IUserService;
import com.yberdaliyev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView doPost(HttpSession session,
//                               @RequestParam(name = "userlogin") String login,
//                               @RequestParam(name = "userpassword") String password) {
//        ModelAndView modelAndView;
//        User user = userService.authorize(login,password);
//        session.setAttribute("user_object",user);
//        session.setMaxInactiveInterval(10*60);
//        session.setAttribute("incorrect_login",null);
//
//        if (user instanceof Admin) {
//            session.setAttribute("user_role",3);
//            modelAndView = new ModelAndView("redirect:/admin_account");
//        } else
//        if (user instanceof Driver) {
//            session.setAttribute("user_role",2);
//            modelAndView = new ModelAndView("redirect:/driver_account");
//        } else
//        if (user instanceof Client) {
//            session.setAttribute("user_role",1);
//            modelAndView = new ModelAndView("redirect:/user_account");
//        } else {
//            session.setAttribute("incorrect_login",1);
//            modelAndView = new ModelAndView("index");
//        }
//        return modelAndView;
//    }

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpSession session)  {
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex( )  {
        return "index";
    }

}
