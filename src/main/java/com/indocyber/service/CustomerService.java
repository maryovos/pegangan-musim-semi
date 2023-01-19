package com.indocyber.service;

import com.indocyber.dto.customer.CustomerDto;
import com.indocyber.dto.customer.CustomerDtoUpdate;
import com.indocyber.entity.Customer;

import java.util.List;

public interface CustomerService {
    void save(CustomerDto customerDto);

    List<Customer> findAll();

    Customer findById(String idCustomer);

    void update(Customer customer, CustomerDto customerDto);

    void update(Customer customer, CustomerDtoUpdate customerDtoUpdate);

    void deleteById(String idCustomer);

    void extendExpireDateById(Customer customer);

    List<Customer> findByExpireDate();

    List<Customer> findByMembershipNumber(String searchNumber);

    List<Customer> findByName(String searchName);

    boolean checkExistingAccount(String membershipNumber);

    List<Customer> findBySearch(String searchName, String searchNumber);
}
