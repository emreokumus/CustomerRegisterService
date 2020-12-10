package com.example.customer_register.service;

import com.example.customer_register.entity.Customer;
import com.example.customer_register.repository.ICustomerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class ServiceTest {

    @Spy
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private ICustomerRepository customerRepository;


    @Test
    @SneakyThrows
    public void shouldFindOwners() {
        Customer customer = Customer.builder().customerName("Emre").customerSurname("Okumus").build();
        List<Customer> customerList = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn((customerList));
        assertEquals(1, customerService.listAllCustomers().size());
    }


    @Test
    @SneakyThrows
    public void shouldSaveAccount() {
        Customer customer = Customer.builder().customerName("Emre").customerSurname("Okumus").build();
        when(customerRepository.save(customer)).thenReturn(customer);
        assertEquals(customer.getCustomerName(), customerRepository.save(customer).getCustomerName());
    }


}
