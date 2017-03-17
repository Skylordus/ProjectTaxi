package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import com.yberdaliyev.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Yerlan on 27.02.2017.
 */
@Controller
public class DriverServlet {
    private static Logger logger = Logger.getLogger(DriverServlet.class);
    private IOrderService orderService;
    private IClientService clientService;
    private ICarService carService;
    private IDriverService driverService;

    @Autowired
    public void setDriverService(IDriverService driverService) {this.driverService = driverService;}
    @Autowired
    public void setCarService(ICarService carService) {
        this.carService = carService;
    }
    @Autowired
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }
    @Autowired
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }


    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @RequestMapping(value = "/driver_account", method = RequestMethod.POST)
    public ModelAndView doPost(HttpSession session,
                               @RequestParam(name="order_id") Long order_id,
                               @RequestParam(name="type") String type,
                               @RequestParam(name="client_id", required = false) Long client_id)  {
        logger.warn("on doPost DriverServlet");
        logger.warn("Order ID: " + order_id);
        ModelAndView modelAndView = new ModelAndView("driver_account");
        Driver driver = (Driver) session.getAttribute("user_object");
        Long driver_id = driver.getId();

        if ( type.equals("pick") ) {
            String status = String.valueOf(OrderService.STATUS_NOT_STARTED);
            boolean isUpdated = orderService.updateOrder(order_id.toString(),
                    "",
                    "",
                    "",
                    "",
                    driver_id.toString(),
                    status,
                    "");
            if (!isUpdated) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause", "order not updated. in DriverServlet");
                return modelAndView;
            }
            isUpdated = driverService.updateDriver(driver_id.toString(),
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    order_id.toString());
            if (!isUpdated) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause", "driver not updated. in DriverServlet");
                return modelAndView;
            }
        } else
        if ( type.equals("on_execution") ) {
            orderService.setStatus(order_id,OrderService.STATUS_FULFILLING);
        } else
        if ( type.equals("finished") ) {
            orderService.delete(order_id);
            order_id = 0l;
            clientService.increaseOrdersCount(client_id);
        }
        driver.setOrder(order_id);
        session.setAttribute("user_object", driver);
        return doGet(session);
    }


    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @RequestMapping(value = "/driver_account", method = RequestMethod.GET)
    public ModelAndView doGet(HttpSession session)  {
        logger.warn("on doGet DriverServlet");
        ModelAndView modelAndView = new ModelAndView("driver_account");
        Driver driver = (Driver) session.getAttribute("user_object");
        Long orderID = driver.getOrder();

        boolean showOrder = false;
        Order order = null;
        if (orderID!=null) {
           if (orderID!=0) {
               order = orderService.getOrder(orderID);
               if ( (order.getStatus()!=OrderService.STATUS_FINISHED)&&(order.getStatus()!=OrderService.STATUS_NO_DRIVER)) {
                   showOrder=true;
               }
           }
        }

        if (showOrder) {
            modelAndView.addObject("your_order",order);
            Client client = clientService.getClient(order.getClient());
            String clientName = client.getFirstname()+" "+client.getPatronymic()+" "+client.getLastname();
            modelAndView.addObject("client",clientName);
            Car car = carService.getCar(driver.getCar());
            String carName = "No car";
            if (car!=null) carName = car.toString();
            modelAndView.addObject("car",carName);
            modelAndView.addObject("status", OrderService.STATUS_MESSAGES[order.getStatus().intValue()]);
            modelAndView.addObject("isPicked",true);
        } else {
            ArrayList<Order> orders = orderService.getFreeOrders();
            modelAndView.addObject("orders",orders);
            List<Long> list = new ArrayList<>();
            for (Order o:orders) {
                list.add(o.getClient());
            }
            HashMap clients = clientService.getClientNamesMappedById(list);
            modelAndView.addObject("clients",clients);
            modelAndView.addObject("isPicked",false);
        }
        return modelAndView;
    }



}
