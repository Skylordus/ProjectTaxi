package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.forms.OrderTableForm;
import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import org.apache.log4j.Logger;
import com.yberdaliyev.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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


    @PostMapping("/client_account")
    public ModelAndView doPost(HttpSession session,
                               @RequestParam(name = "type") String type,
                               @ModelAttribute OrderTableForm form) {
        ModelAndView modelAndView = new ModelAndView("client_account");
        Client client = (Client) session.getAttribute("user_object");
        logger.warn("in do Post type = "+type);

        if (type == null) {} else
        if (type.equals("new_order")){

            Order order = orderService.generateOrder(null,
                    form.getFrom(),
                    form.getTo(),
                    form.getPrice(),
                    client,
                    null,
                    0,
                    form.getPickup_time());

            Long id = orderService.insert(order);
            logger.error("in new order = "+order.toString());
            order.setId(id);
            client.setOrder(order);
            clientService.updateOrder(client.getId(),id);

        } else if (type.equals("cancel_order")) {
            logger.trace(client.getId());
            orderService.delete(client.getOrder().getId());
            client.setOrder(null);
        }
        session.setAttribute("user_object",client);
        return generateUserPage(modelAndView, client);
    }


    @GetMapping(value = "/client_account")
    public ModelAndView doGet(HttpSession session) {
        logger.warn("on doGET client Servlet");
        ModelAndView modelAndView = new ModelAndView("client_account");
        Client client = (Client) session.getAttribute("user_object");
        return generateUserPage(modelAndView,client);
    }

    private ModelAndView generateUserPage(ModelAndView modelAndView, Client client) {
        modelAndView.addObject("login",client.getFirstname());
        Long orderId = client.getOrder().getId();
        if ( (orderId == 0)||(orderId == null) ) {
            modelAndView.addObject("ordered",null);
        } else {
            modelAndView.addObject("ordered","true");
            Order order = orderService.getOrder(client.getOrder().getId());
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
            i=order.getDriver().getId();
            String str2;
            if (i>0) {
                Driver driver = driverService.getDriver(i);
                str=driver.toString();
                Car car = carService.getCar(driver.getCar().getId());
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
