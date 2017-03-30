package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import com.yberdaliyev.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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


    @PostMapping("/driver_account")
    public ModelAndView doPost(HttpSession session,
                               @RequestParam(name="order_id") Long order_id,
                               @RequestParam(name="type") String type,
                               @RequestParam(name="client_id", required = false) Long client_id)  {
        logger.warn("on doPost DriverServlet");
        logger.warn("Order ID: " + order_id);
        ModelAndView modelAndView = new ModelAndView("driver_account");
        Driver driver = (Driver) session.getAttribute("user_object");
        Order order = new Order();
        order.setId(order_id);
        order.setDriver(driver);

        if ( type.equals("pick") ) {
            String status = String.valueOf(OrderService.STATUS_NOT_STARTED);

            driverService.updateDriver(driver.getId(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    order_id);
            orderService.setStatus(order_id,OrderService.STATUS_NOT_STARTED);
        } else
        if ( type.equals("on_execution") ) {
            orderService.setStatus(order_id,OrderService.STATUS_FULFILLING);
        } else
        if ( type.equals("finished") ) {
            orderService.delete(order_id);
            order_id = 0L;
            clientService.increaseOrdersCount(client_id);
        }
        driver.setOrder(order.getId());
        session.setAttribute("user_object", driver);
        return doGet(session);
    }


    @GetMapping("/driver_account")
    public ModelAndView doGet(HttpSession session)  {
        logger.warn("on doGet DriverServlet");
        ModelAndView modelAndView = new ModelAndView("driver_account");
        Driver driver = (Driver) session.getAttribute("user_object");
        logger.warn("drivers order="+driver.getOrder());
        boolean showOrder = false;
        Order order = orderService.getOrder(driver.getOrder());
        if (order!=null) {
            if ( (order.getStatus()!=OrderService.STATUS_FINISHED)&&(order.getStatus()!=OrderService.STATUS_NO_DRIVER)) {
                showOrder=true;
            }
        }

        if (showOrder) {
            modelAndView.addObject("your_order",order);
            Client client = order.getClient();
            String clientName = client.getFirstname()+" "+client.getPatronymic()+" "+client.getLastname();
            modelAndView.addObject("client",clientName);
            Car car = carService.getCar(driver.getCar());
            String carName = "No car";
            if (car!=null) carName = car.toString();
            modelAndView.addObject("car",carName);
            modelAndView.addObject("status", OrderService.STATUS_MESSAGES[order.getStatus()]);
            modelAndView.addObject("isPicked",true);
        } else {
            List<Order> orders = orderService.getFreeOrders();
            modelAndView.addObject("orders",orders);
//            List<Long> list = new ArrayList<>();
//            for (Order o:orders) {
//                list.add(o.getClient());
//            }
//            HashMap clients = clientService.getClientNamesMappedById(list);
//            modelAndView.addObject("clients",clients);
            modelAndView.addObject("isPicked",false);
        }
        return modelAndView;
    }



}
