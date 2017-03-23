package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.models.forms.RegistrationForm;
import com.yberdaliyev.models.repositories.LoginRepository;
import com.yberdaliyev.services.IUserService;
import org.apache.log4j.Logger;
import com.yberdaliyev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/registration")
    public ModelAndView regFormSubmit(@Valid @ModelAttribute("regForm") RegistrationForm form, BindingResult result) {
        logger.warn("on POST Registration servlet");
        logger.warn("Binding result="+result);
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("special_password", "none");

        boolean hasErrors = false;
        if (!userService.validateSpecialPassword(form.getSpecial_password(),form.getUser_role())) {
            hasErrors=true;
            modelAndView.addObject("special_password", "inline");
        }
        if (result.hasErrors()) {
            hasErrors=true;
        }
        if (hasErrors) return modelAndView;

        userService.register(form.getUser_role(),
                             form.getUser_name(),
                             form.getUser_surname(),
                             form.getUser_patronymic(),
                             form.getUser_birthdate(),
                             form.getUser_login(),
                             form.getUser_password(),
                             form.getUser_email());

        return modelAndView;
    }

    @GetMapping("/registration")
    public String showRegForm( Model model ) {
        logger.warn("on GET Registration servlet");
        RegistrationForm regForm = new RegistrationForm();
        model.addAttribute("special_password","none");
        model.addAttribute("regForm",regForm);
        return "registration";
    }


}
