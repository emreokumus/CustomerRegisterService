package com.example.customer_register.repository;

import com.example.customer_register.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long>{

    boolean existsCustomerByCustomerNameAndCustomerSurname(String customerName,String customerSurname);
}
