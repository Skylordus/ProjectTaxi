package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.forms.RegistrationForm;
import com.yberdaliyev.services.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

        logger.warn("ROLE: " + form.getUser_role());
        userService.register(form.getUser_role(),
                             form.getUser_name(),
                             form.getUser_surname(),
                             form.getUser_patronymic(),
                             form.getUser_birthdate(),
                             form.getUser_login(),
                             form.getUser_password(),
                             form.getUser_email());

        modelAndView.setViewName("index");
        modelAndView.addObject("regsuccess",true);

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
