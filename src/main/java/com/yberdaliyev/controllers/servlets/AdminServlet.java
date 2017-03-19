package com.yberdaliyev.controllers.servlets;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.sun.tracing.dtrace.ModuleAttributes;
import com.yberdaliyev.models.entities.ClientEntity;
import com.yberdaliyev.models.pojos.*;
import com.yberdaliyev.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerlan on 27.02.2017.
 */
@Controller
public class AdminServlet {
    private static Logger logger = Logger.getLogger(AdminServlet.class);
    private IOrderService orderService;
    private IClientService clientService;
    private IDriverService driverService;
    private IAdminService adminService;
    private ICarService carService;

    @Autowired
    public void setCarService(ICarService carService) {this.carService = carService;}
    @Autowired
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setClientService(IClientService clientService) {this.clientService = clientService;}
    @Autowired
    public void setAdminService(IAdminService adminService) {this.adminService = adminService;}
    @Autowired
    public void setDriverService(IDriverService driverService) {this.driverService = driverService;}

    //    @ExceptionHandler(value = Exception.class)
//    public void doPost(){
//
//    }


    @RequestMapping(value = "/admin_account", method = RequestMethod.GET)
    public ModelAndView doGet() {
        logger.warn("on doGet AdminServlet");
        ModelAndView modelAndView = new ModelAndView("admin_account");

        ArrayList<Order> orders = orderService.getAll();
        List<ClientEntity> clients = clientService.getAll();
        ArrayList<Driver> drivers = driverService.getAll();
        ArrayList<Admin> admins = adminService.getAll();
        ArrayList<Car> cars = carService.getAll();

        modelAndView.addObject("clients",clients);
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("drivers",drivers);
        modelAndView.addObject("admins",admins);
        modelAndView.addObject("cars",cars);
        modelAndView.addObject("showtable",1);
        return modelAndView;
    }


    @RequestMapping(value = "/admin_account", method = RequestMethod.POST)
    public ModelAndView tableOrders (@RequestParam(name = "type") String type,
                               @RequestParam(name = "id", required=false) String id,
                               @RequestParam(name = "from", required=false) String from,
                               @RequestParam(name = "to", required=false) String to,
                               @RequestParam(name = "client_id", required=false) String client,
                               @RequestParam(name = "driver_id", required=false) String driver,
                               @RequestParam(name = "price", required=false) String price,
                               @RequestParam(name = "status", required=false) String status,
                               @RequestParam(name = "pickup_time", required=false) String pickup_time) {

        logger.warn("on doPOST AdminServlet");
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",1);
        if (type==null) {} else
        if (type.equals("edit")) {
            boolean isUpdated = orderService.updateOrder(id,
                                                      from,
                                                      to,
                                                      price,
                                                      client,
                                                      driver,
                                                      status,
                                                      pickup_time);
            if (!isUpdated) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","order not updated. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("delete")) {
            boolean isDeleted = orderService.delete(Long.parseLong(id));
            if (!isDeleted) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","order not deleted. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("add")) {
            Order order = orderService.generateOrder("0",
                                                    from,
                                                    to,
                                                    price,
                                                    client,
                                                    driver,
                                                    status,
                                                    pickup_time);
            Long isAddedCode = orderService.insert(order, false);
            if (isAddedCode==null) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","order not inserted. in AdminServlet");
                return modelAndView;
            }
        }

        ArrayList<Order> orders = orderService.getAll();
        List<ClientEntity> clients = clientService.getAll();
        ArrayList<Driver> drivers = driverService.getAll();
        ArrayList<Admin> admins = adminService.getAll();
        ArrayList<Car> cars = carService.getAll();

        modelAndView.addObject("clients",clients);
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("drivers",drivers);
        modelAndView.addObject("admins",admins);
        modelAndView.addObject("cars",cars);
        return modelAndView;
    }


    @RequestMapping(value = "/admin_account/client", method = RequestMethod.POST)
    public ModelAndView tableClient(@RequestParam(name = "type") String type,
                               @RequestParam(name = "id", required=false) String id,
                               @RequestParam(name = "firstname", required=false) String firstname,
                               @RequestParam(name = "lastname", required=false) String lastname,
                               @RequestParam(name = "patronymic", required=false) String patronymic,
                               @RequestParam(name = "date_registered", required=false) String date_registered,
                               @RequestParam(name = "orders_amount", required=false) String orders_amount,
                               @RequestParam(name = "birthdate", required=false) String birthdate,
                               @RequestParam(name = "login", required=false) String login,
                               @RequestParam(name = "email", required=false) String email,
                               @RequestParam(name = "order", required=false) String order,
                               @RequestParam(name = "pwd", required=false) String pwd) {
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",2);

        if (type==null) {} else
        if (type.equals("edit")) {
            boolean isUpdated = clientService.updateClient(id,
                                                            firstname,
                                                            lastname,
                                                            patronymic,
                                                            date_registered,
                                                            orders_amount,
                                                            birthdate,
                                                            login,
                                                            email,
                                                            order);
            if (!isUpdated) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","order not updated. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("delete")) {
            boolean isDeleted = clientService.delete(Long.parseLong(id));
            if (!isDeleted) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","order not deleted. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("add")) {
            ClientEntity client = clientService.generateClient("0",
                    firstname,
                    lastname,
                    patronymic,
                    date_registered,
                    orders_amount,
                    birthdate,
                    login,
                    email,
                    order);
            client.setPwd(pwd);
            Long isAddedCode = clientService.insert(client, false);
            if (isAddedCode == null) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause", "client not inserted. in AdminServlet");
                return modelAndView;
            }
        }
        ArrayList<Order> orders = orderService.getAll();
        List<ClientEntity> clients = clientService.getAll();
        ArrayList<Driver> drivers = driverService.getAll();
        ArrayList<Admin> admins = adminService.getAll();
        ArrayList<Car> cars = carService.getAll();

        modelAndView.addObject("clients",clients);
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("drivers",drivers);
        modelAndView.addObject("admins",admins);
        modelAndView.addObject("cars",cars);
        return modelAndView;
    }


    @RequestMapping(value = "/admin_account/driver", method = RequestMethod.POST)
    public ModelAndView tableDriver(@RequestParam(name = "type") String type,
                                    @RequestParam(name = "id", required=false) String id,
                                    @RequestParam(name = "firstname", required=false) String firstname,
                                    @RequestParam(name = "lastname", required=false) String lastname,
                                    @RequestParam(name = "patronymic", required=false) String patronymic,
                                    @RequestParam(name = "birthdate", required=false) String birthdate,
                                    @RequestParam(name = "login", required=false) String login,
                                    @RequestParam(name = "email", required=false) String email,
                                    @RequestParam(name = "order", required=false) String order,
                                    @RequestParam(name = "experience_years", required=false) String experience_years,
                                    @RequestParam(name = "car", required=false) String car,
                                    @RequestParam(name = "pwd", required=false) String pwd) {
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",3);

        if (type==null) {} else
        if (type.equals("edit")) {
            boolean isUpdated = driverService.updateDriver(id,
                                                            firstname,
                                                            lastname,
                                                            patronymic,
                                                            experience_years,
                                                            car,
                                                            birthdate,
                                                            login,
                                                            email,
                                                            order);
            if (!isUpdated) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","driver not updated. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("delete")) {
            boolean isDeleted = driverService.delete(Long.parseLong(id));
            if (!isDeleted) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","driver not deleted. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("add")) {
            Driver driver = driverService.generateDriver("0",
                                                        firstname,
                                                        lastname,
                                                        patronymic,
                                                        experience_years,
                                                        car,
                                                        birthdate,
                                                        login,
                                                        email,
                                                        order);
            driver.setPwd(pwd);
            Long isAddedCode = driverService.insert(driver, false);
            if (isAddedCode == null) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause", "driver not inserted. in AdminServlet");
                return modelAndView;
            }
        }
        ArrayList<Order> orders = orderService.getAll();
        List<ClientEntity> clients = clientService.getAll();
        ArrayList<Driver> drivers = driverService.getAll();
        ArrayList<Admin> admins = adminService.getAll();
        ArrayList<Car> cars = carService.getAll();

        modelAndView.addObject("clients",clients);
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("drivers",drivers);
        modelAndView.addObject("admins",admins);
        modelAndView.addObject("cars",cars);
        return modelAndView;
    }


    @RequestMapping(value = "/admin_account/admin", method = RequestMethod.POST)
    public ModelAndView tableAdmin(@RequestParam(name = "type") String type,
                                    @RequestParam(name = "id", required=false) String id,
                                    @RequestParam(name = "firstname", required=false) String firstname,
                                    @RequestParam(name = "lastname", required=false) String lastname,
                                    @RequestParam(name = "patronymic", required=false) String patronymic,
                                    @RequestParam(name = "birthdate", required=false) String birthdate,
                                    @RequestParam(name = "login", required=false) String login,
                                    @RequestParam(name = "email", required=false) String email,
                                    @RequestParam(name = "pwd", required=false) String pwd) {
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",4);

        if (type==null) {} else
        if (type.equals("edit")) {
            boolean isUpdated = adminService.updateAdmin(id,
                    firstname,
                    lastname,
                    patronymic,
                    birthdate,
                    login,
                    email);
            if (!isUpdated) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","admin not updated. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("delete")) {
            boolean isDeleted = adminService.delete(Long.parseLong(id));
            if (!isDeleted) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","admin not deleted. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("add")) {
            Admin admin = adminService.generateAdmin("0",
                    firstname,
                    lastname,
                    patronymic,
                    birthdate,
                    login,
                    email);
            admin.setPwd(pwd);
            Long isAddedCode = adminService.insert(admin, false);
            if (isAddedCode == null) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause", "admin not inserted. in AdminServlet");
                return modelAndView;
            }
        }
        ArrayList<Order> orders = orderService.getAll();
        List<ClientEntity> clients = clientService.getAll();
        ArrayList<Driver> drivers = driverService.getAll();
        ArrayList<Admin> admins = adminService.getAll();
        ArrayList<Car> cars = carService.getAll();

        modelAndView.addObject("clients",clients);
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("drivers",drivers);
        modelAndView.addObject("admins",admins);
        modelAndView.addObject("cars",cars);
        return modelAndView;
    }


    @RequestMapping(value = "/admin_account/car", method = RequestMethod.POST)
    public ModelAndView tableCar(@RequestParam(name = "type") String type,
                                   @RequestParam(name = "id", required=false) String id,
                                   @RequestParam(name = "manufacturer", required=false) String manufacturer,
                                   @RequestParam(name = "model", required=false) String model,
                                   @RequestParam(name = "regnum", required=false) String regnum,
                                   @RequestParam(name = "color", required=false) String color) {
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",5);

        if (type==null) {} else
        if (type.equals("edit")) {
            boolean isUpdated = carService.updateCar(id,
                                                     manufacturer,
                                                     model,
                                                     regnum,
                                                     color);
            if (!isUpdated) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","car not updated. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("delete")) {
            boolean isDeleted = carService.delete(Long.parseLong(id));
            if (!isDeleted) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause","car not deleted. in AdminServlet");
                return modelAndView;
            }
        } else
        if (type.equals("add")) {
            Car car = carService.generateCar("0",
                                            manufacturer,
                                            model,
                                            regnum,
                                            color);
            Long isAddedCode = carService.insert(car, false);
            if (isAddedCode == null) {
                modelAndView.setViewName("failed");
                modelAndView.addObject("cause", "car not inserted. in AdminServlet");
                return modelAndView;
            }
        }
        ArrayList<Order> orders = orderService.getAll();
        List<ClientEntity> clients = clientService.getAll();
        ArrayList<Driver> drivers = driverService.getAll();
        ArrayList<Admin> admins = adminService.getAll();
        ArrayList<Car> cars = carService.getAll();

        modelAndView.addObject("clients",clients);
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("drivers",drivers);
        modelAndView.addObject("admins",admins);
        modelAndView.addObject("cars",cars);
        return modelAndView;
    }
}
