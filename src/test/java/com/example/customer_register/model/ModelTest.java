package com.example.customer_register.model;

import com.example.customer_register.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ModelTest {

    @Test
    public void testRegisterCustomer() {
        Customer customer = Customer.builder().customerName("Emre").customerSurname("Okumus").build();
        assertTrue(customer.getCustomerName().equals("Emre"));
        assertTrue(customer.getCustomerSurname().equals("Okumus"));
    }
}
