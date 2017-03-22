package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.forms.*;
import com.yberdaliyev.models.pojos.*;
import com.yberdaliyev.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

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
    private void addTablesToModel(ModelAndView modelAndView) {
        List<Order> orders = orderService.getAll();
        List<Client> clients = clientService.getAll();
        List<Driver> drivers = driverService.getAll();
        List<Admin> admins = adminService.getAll();
        List<Car> cars = carService.getAll();
        modelAndView.addObject("clients",clients);
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("drivers",drivers);
        modelAndView.addObject("admins",admins);
        modelAndView.addObject("cars",cars);
    }


    @GetMapping("/admin_account")
    public ModelAndView doGet() {
        logger.warn("on doGet AdminServlet");
        ModelAndView modelAndView = new ModelAndView("admin_account");
        addTablesToModel(modelAndView);
        modelAndView.addObject("showtable",1);
        return modelAndView;
    }


    @PostMapping("/admin_account")
    public ModelAndView tableOrders (@RequestParam(name = "type") String type,
                                     @ModelAttribute OrderTableForm form) {

        logger.warn("on tableOrders AdminServlet");
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",1);

        Client client = new Client();
        client.setId(form.getClient_id());
        Driver driver = new Driver();
        driver.setId(form.getDriver_id());

        if (type==null) {} else
        if (type.equals("edit")) {

            orderService.updateOrder(form.getId(),
                    form.getFrom(),
                    form.getTo(),
                    form.getPrice(),
                    client,
                    driver,
                    form.getStatus(),
                    form.getPickup_time());
        } else
        if (type.equals("delete")) {
            orderService.delete(form.getId());
        } else
        if (type.equals("add")) {
            Order order = orderService.generateOrder(null,
                    form.getFrom(),
                    form.getTo(),
                    form.getPrice(),
                    client,
                    driver,
                    form.getStatus(),
                    form.getPickup_time());
        }

        addTablesToModel(modelAndView);
        return modelAndView;
    }


    @PostMapping("/admin_account/client")
    public ModelAndView tableClient(@RequestParam(name = "type") String type,
                                    @ModelAttribute ClientTableForm form) {
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",2);

        Order order = new Order();
        order.setId(form.getOrder());

        if (type==null) {} else
        if (type.equals("edit")) {
            clientService.updateClient(form.getId(),
                    form.getFirstname(),
                    form.getLastname(),
                    form.getPatronymic(),
                    form.getDate_registered(),
                    form.getOrders_amount(),
                    form.getBirthdate(),
                    form.getLogin(),
                    form.getEmail(),
                    order);
        } else
        if (type.equals("delete")) {
            clientService.delete(form.getId());
        } else
        if (type.equals("add")) {
            Client client = clientService.generateClient(null,
                    form.getFirstname(),
                    form.getLastname(),
                    form.getPatronymic(),
                    form.getDate_registered(),
                    form.getOrders_amount(),
                    form.getBirthdate(),
                    form.getLogin(),
                    form.getEmail(),
                    order);
            client.setPwd(form.getPwd());
            clientService.insert(client);
        }

        addTablesToModel(modelAndView);
        return modelAndView;
    }


    @PostMapping("/admin_account/driver")
    public ModelAndView tableDriver(@RequestParam(name = "type") String type,
                                    @ModelAttribute DriverTableForm form) {
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",3);

        Car car = new Car();
        car.setId(form.getCar());
        Order order = new Order();
        order.setId(form.getOrder());

        if (type==null) {} else
        if (type.equals("edit")) {
            driverService.updateDriver(form.getId(),
                    form.getFirstname(),
                    form.getLastname(),
                    form.getPatronymic(),
                    form.getExperience_years(),
                    car,
                    form.getBirthdate(),
                    form.getLogin(),
                    form.getEmail(),
                    order);

        } else
        if (type.equals("delete")) {
            driverService.delete(form.getId());

        } else
        if (type.equals("add")) {
            Driver driver = driverService.generateDriver(null,
                                                        form.getFirstname(),
                                                        form.getLastname(),
                                                        form.getPatronymic(),
                                                        form.getExperience_years(),
                                                        car,
                                                        form.getBirthdate(),
                                                        form.getLogin(),
                                                        form.getEmail(),
                                                        order);
            driver.setPwd(form.getPwd());
            driverService.insert(driver);
        }
        addTablesToModel(modelAndView);
        return modelAndView;
    }


    @PostMapping("/admin_account/admin")
    public ModelAndView tableAdmin(@RequestParam(name = "type") String type,
                                   @ModelAttribute AdminTableForm form) {
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",4);

        if (type==null) {} else
        if (type.equals("edit")) {
            adminService.updateAdmin(form.getId(),
                    form.getFirstname(),
                    form.getLastname(),
                    form.getPatronymic(),
                    form.getBirthdate(),
                    form.getLogin(),
                    form.getEmail());
        } else
        if (type.equals("delete")) {
            adminService.delete(form.getId());
        } else
        if (type.equals("add")) {
            Admin admin = adminService.generateAdmin(0L,
                    form.getFirstname(),
                    form.getLastname(),
                    form.getPatronymic(),
                    form.getBirthdate(),
                    form.getLogin(),
                    form.getEmail());
            admin.setPwd(form.getPwd());
            adminService.insert(admin);
        }
        addTablesToModel(modelAndView);
        return modelAndView;
    }


    @PostMapping("/admin_account/car")
    public ModelAndView tableCar(@RequestParam(name = "type") String type,
                                 @ModelAttribute CarTableForm form) {
        ModelAndView modelAndView = new ModelAndView("admin_account");
        modelAndView.addObject("showtable",5);
        Driver driver = new Driver();
        driver.setId(form.getDriver_id());
        if (type==null) {} else
        if (type.equals("edit")) {
            carService.updateCar(form.getId(),
                    form.getManufacturer(),
                    form.getModel(),
                    form.getRegnum(),
                    form.getColor(),
                    driver);

        } else
        if (type.equals("delete")) {
            carService.delete(form.getId());
        } else
        if (type.equals("add")) {
            Car car = carService.generateCar(0L,
                                            form.getManufacturer(),
                                            form.getModel(),
                                            form.getRegnum(),
                                            form.getColor());
            carService.insert(car);
        }

        addTablesToModel(modelAndView);
        return modelAndView;
    }
}
