package com.example.customer_register.service;

import com.example.customer_register.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> listAllCustomers();
    String saveCustomer(Customer customer);
}
