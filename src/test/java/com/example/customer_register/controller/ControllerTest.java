package com.example.customer_register.controller;

import com.example.customer_register.entity.Customer;
import com.example.customer_register.service.ICustomerService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.sun.tools.doclint.Entity.times;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ICustomerService customerService;

    @InjectMocks
    private CustomerController controller;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @SneakyThrows
    public void shouldListCustomers() {
        Customer customer =Customer.builder().customerName("Emre").customerSurname("Okumus").build();
        List<Customer> customerList = Arrays.asList(customer);
        when(customerService.listAllCustomers()).thenReturn(customerList);

        mockMvc.perform(MockMvcRequestBuilders.get("/listAllCustomers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("customers.html"))
                .andExpect(MockMvcResultMatchers.model().attribute("customerList", hasSize(1)))
                .andExpect(MockMvcResultMatchers.model().attribute("customerList", hasItem(
                        allOf(
                                hasProperty("customerName", is("Emre")),
                                hasProperty("customerSurname", is("Okumus"))
                        )
                )));
        verify(customerService, times(1)).listAllCustomers();
        verifyNoMoreInteractions(customerService);
    }


    @Test
    @SneakyThrows
    public void shouldSaveCustomers() {
        Customer customer =Customer.builder().customerName("Emre").customerSurname("Okumus").city("Istanbul").build();
        when(customerService.saveCustomer(customer)).thenReturn("Kayit basarili");

        mockMvc.perform(MockMvcRequestBuilders.post("/save")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .param("customerName","Emre")
                        .param("customerSurname","Okumus")
                        .param("city","Istanbul")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register.html"));
               // .andExpect(MockMvcResultMatchers.model().attribute("customer", hasProperty("customerName", is("Emre"))))
               // .andExpect(MockMvcResultMatchers.model().attribute("customer", hasProperty("customerSurname", is("Okumus"))));

    }

}
