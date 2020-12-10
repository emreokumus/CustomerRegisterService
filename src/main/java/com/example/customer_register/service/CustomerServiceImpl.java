package com.example.customer_register.service;

import com.example.customer_register.entity.Customer;
import com.example.customer_register.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public String saveCustomer(Customer customer) {
        if(customerRepository.existsCustomerByCustomerNameAndCustomerSurname(customer.getCustomerName(),customer.getCustomerSurname())==true){
            return "Kayıt mevcut.";
        }
        customerRepository.save(customer);
        return "Kayit başarılı şekilde eklendi";
    }

    @Override
    public List<Customer> listAllCustomers() {

            return customerRepository.findAll();

    }
}
