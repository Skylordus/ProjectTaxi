package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import org.apache.log4j.Logger;
import com.yberdaliyev.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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


    @RequestMapping(value = "/client_account", method = RequestMethod.POST)
    public ModelAndView doPost(HttpSession session,
                               @RequestParam(name = "type") String type,
                               @RequestParam(name = "from", required = false) String from,
                               @RequestParam(name = "to", required = false) String to,
                               @RequestParam(name = "pickup_time", required = false) String pickup_time,
                               @RequestParam(name = "plan", required = false) String plan) {
        ModelAndView modelAndView = new ModelAndView("client_account");
        Client client = (Client) session.getAttribute("user_object");
        logger.warn("in do Post type = "+type);

        if (type == null) {} else
        if (type.equals("new_order")){
            Order order = orderService.generateOrder(client,
                                        from,
                                        to,
                                        pickup_time,
                                        plan);
            Long id = orderService.insert(order, true);
            logger.error("in new order = "+order.toString());
            client.setOrder(id);
            clientService.updateOrder(client.getId(),id);

        } else if (type.equals("cancel_order")) {
            logger.trace(client.getId());
            orderService.delete(client.getOrder());
            client.setOrder((long)0);
        }
        session.setAttribute("user_object",client);
        return generateUserPage(modelAndView, client);
    }


    @RequestMapping(value = "/client_account", method = RequestMethod.GET)
    public ModelAndView doGet(HttpSession session) {
        logger.warn("on doGET client Servlet");
        ModelAndView modelAndView = new ModelAndView("client_account");
        Client client = (Client) session.getAttribute("user_object");
        return generateUserPage(modelAndView,client);
    }

    private ModelAndView generateUserPage(ModelAndView modelAndView, Client client) {
        modelAndView.addObject("login",client.getFirstname());
        Long orderId = client.getOrder();
        if ( (orderId == 0)||(orderId == null) ) {
            modelAndView.addObject("ordered",null);
        } else {
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
            i=order.getDriver();
            String str2;
            if (i>0) {
                Driver driver = driverService.getDriver(i);
                str=driver.toString();
                Car car = carService.getCar(driver.getCar());
                if (car!=null) {str2=car.toString();}
                else {str2="not assigned yet";}
            } else {
                str="not assigned yet";
                str2="not assigned yet";
            }
            modelAndView.addObject("driver",str);
            modelAndView.addObject("car",str2);
            modelAndView.addObject("status",OrderService.STATUS_MESSAGES[order.getStatus().intValue()]);
        }
        return modelAndView;
    }

}
