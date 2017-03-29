package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.forms.OrderTableForm;
import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import org.apache.log4j.Logger;
import com.yberdaliyev.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yerlan on 27.02.2017.
 */
@Controller
public class ClientServlet {
    private static Logger logger = Logger.getLogger(ClientServlet.class);
    private IOrderService orderService;
    private IClientService clientService;
    private IDriverService driverService;
    private ICarService carService;

    @Autowired
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }
    @Autowired
    public void setDriverService(IDriverService driverService) {
        this.driverService = driverService;
    }
    @Autowired
    public void setCarService(ICarService carService) {
        this.carService = carService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/client_account")
    public ModelAndView doPost(HttpSession session,
                               @Valid @ModelAttribute("orderForm") OrderTableForm form,
                               BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("client_account");
        Client client = (Client) session.getAttribute("user_object");
        logger.warn("Binding result="+result);
        logger.warn("in posting new order, client servlet");
        if (result.hasErrors()) {
            return modelAndView;
        }
        Order order = orderService.generateOrder(null,
                form.getFrom(),
                form.getTo(),
                form.getPrice(),
                new Client(client.getId()),
                null,
                0,
                form.getPickup_time());
        Long orderid = orderService.insert(order);
        logger.warn("new order insertion complete, order id="+orderid);
        order.setId(orderid);
        client.setOrder(order.getId());
        clientService.updateOrder(client.getId(),orderid);
        session.setAttribute("user_object",client);
        return generateUserPage(modelAndView, client);
    }


    @GetMapping(value = "/client_account")
    public ModelAndView doGet(HttpSession session) {
        logger.warn("on doGET client Servlet");
        ModelAndView modelAndView = new ModelAndView("client_account");
        Client client = (Client) session.getAttribute("user_object");
        OrderTableForm orderTableForm = new OrderTableForm();
        modelAndView.addObject("orderForm",orderTableForm);
        return generateUserPage(modelAndView,client);
    }

    @PostMapping(value = "/client_account/cancel_order")
    public ModelAndView cancelOrder(HttpSession session) {
        logger.warn("on cancel order client Servlet");
        ModelAndView modelAndView = new ModelAndView("client_account");
        Client client = (Client) session.getAttribute("user_object");
        orderService.delete(client.getOrder());
        client.setOrder(null);
        session.setAttribute("user_object",client);
        OrderTableForm orderTableForm = new OrderTableForm();
        modelAndView.addObject("orderForm",orderTableForm);
        logger.warn("calling gen.userpage from cancelOrder, orderid"+client.getOrder());
        return generateUserPage(modelAndView,client);
    }

    private ModelAndView generateUserPage(ModelAndView modelAndView, Client client) {
        modelAndView.addObject("login",client.getFirstname());
        Long orderId = client.getOrder();
        logger.warn("Order ID="+orderId);

        if ( (orderId == null) || (orderId == 0) ) {
            logger.warn("in order null clause: ");
            modelAndView.addObject("ordered",null);
        } else {
            logger.warn("in order NOT null clause: ");
            modelAndView.addObject("ordered","true");
            Order order = orderService.getOrder(client.getOrder());
            modelAndView.addObject("from",order.getFrom());
            modelAndView.addObject("to",order.getTo());
            modelAndView.addObject("time",order.getPickup_time());
            long i = order.getPrice_per_km();
            String str = ""+i+" Rubles/km";
            if (i==16) {
                str+=" (Economy)";
            } else
            if (i==25) {
                str+=" (Comfort)";
            } else {
                str+=" (Business)";
            }
            modelAndView.addObject("price", str);
            modelAndView.addObject("id",order.getId());
            i=order.getDriver()==null?0:order.getDriver().getId();
            String str2;
            if (i>0) {
                Driver driver = driverService.getDriver(i);
                str=driver.toString();
                Car car = driver.getCar()==null?null:carService.getCar(driver.getCar());
                if (car!=null) {str2=car.toString();}
                else {str2="not assigned yet";}
            } else {
                str="not assigned yet";
                str2="not assigned yet";
            }
            modelAndView.addObject("driver",str);
            modelAndView.addObject("car",str2);
            modelAndView.addObject("status",OrderService.STATUS_MESSAGES[order.getStatus()]);
        }

        return modelAndView;
    }

}
