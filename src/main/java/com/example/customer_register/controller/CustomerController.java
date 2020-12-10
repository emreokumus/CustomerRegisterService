package com.example.customer_register.controller;

import com.example.customer_register.entity.Customer;
import com.example.customer_register.service.CustomerServiceImpl;
import com.example.customer_register.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "index.html";
    }

    @RequestMapping(value = "/registiration", method = RequestMethod.GET)
    public String register() {
        return "register.html";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String registerCustomer(@ModelAttribute("customer") Customer customer, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register.html";
        }
        try {
            model.addAttribute("message",customerService.saveCustomer(customer));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Hata oluştu.");
        } finally {
            return "register.html";
        }
    }

    @RequestMapping(value = "/listAllCustomers", method = RequestMethod.GET)
    public String listAllCustomers(Model model, HttpServletRequest request) {
        try {
            List<Customer> customerList = customerService.listAllCustomers();
            model.addAttribute("customerList", customerList);
            model.addAttribute("message", "Liste başarılı şekilde listelendi.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Hata oluştu.");
        } finally {
            return "customers.html";
        }
    }
}
