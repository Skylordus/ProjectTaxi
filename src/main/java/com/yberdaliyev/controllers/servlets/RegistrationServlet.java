package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.services.IUserService;
import org.apache.log4j.Logger;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Controller
public class RegistrationServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView doPost(@RequestParam(name = "user_role") String user_role,
                               @RequestParam(name = "user_name") String user_name,
                               @RequestParam(name = "user_surname") String user_surname,
                               @RequestParam(name = "user_patronymic") String user_patronymic,
                               @RequestParam(name = "user_birthdate") String user_birthdate,
                               @RequestParam(name = "user_login") String user_login,
                               @RequestParam(name = "user_password") String user_password,
                               @RequestParam(name = "user_email") String user_email) {
        logger.trace("on POST Registration servlet");
        ModelAndView modelAndView = new ModelAndView("registration");
        userService.register(user_role,
                             user_name,
                             user_surname,
                             user_patronymic,
                             user_birthdate,
                             user_login,
                             user_password,
                             user_email);
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String doGet( ) {
        logger.trace("on GET Registration servlet");
        return "registration";
    }



}
