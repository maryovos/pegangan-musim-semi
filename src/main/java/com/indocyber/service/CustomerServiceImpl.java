package com.indocyber.service;

import com.indocyber.dto.customer.CustomerDto;
import com.indocyber.dto.customer.CustomerDtoUpdate;
import com.indocyber.entity.Customer;
import com.indocyber.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(CustomerDto customerDto) {

        Customer customer = new Customer();
        customer.setMembershipNumber(customerDto.getMembershipNumber());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setAddress(customerDto.getAddress());
        customer.setGender(customerDto.getGender());
        customer.setPhone(customerDto.getPhone());
        customer.setMembershipExpireDate(setExpireDate());

        customerRepository.save(customer);

    }

    @Override
    public List<Customer> findAll() {

        List<Customer> customerList = customerRepository.findAll();

        return customerList;
    }

    @Override
    public Customer findById(String idCustomer) {

        Optional<Customer> customerOptional = customerRepository.findById(idCustomer);
        Customer tempCustomer = null;

        if (customerOptional.isPresent()){

            tempCustomer = customerOptional.get();
        }

        return tempCustomer;

    }

    @Override
    public void update(Customer customer, CustomerDto customerDto) {

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setAddress(customerDto.getAddress());
        customer.setGender(customerDto.getGender());
        customer.setPhone(customerDto.getPhone());


        customerRepository.save(customer);

    }

    @Override
    public void update(Customer customer, CustomerDtoUpdate customerDtoUpdate) {

        customer.setFirstName(customerDtoUpdate.getFirstName());
        customer.setLastName(customerDtoUpdate.getLastName());
        customer.setBirthDate(customerDtoUpdate.getBirthDate());
        customer.setAddress(customerDtoUpdate.getAddress());
        customer.setGender(customerDtoUpdate.getGender());
        customer.setPhone(customerDtoUpdate.getPhone());


        customerRepository.save(customer);


    }

    @Override
    public void deleteById(String idCustomer) {
        customerRepository.deleteById(idCustomer);
    }

    @Override
    public void extendExpireDateById(Customer customer) {

        LocalDate oldExpireDate = customer.getMembershipExpireDate();

        customer.setMembershipExpireDate(extendExpireDate(oldExpireDate));

        customerRepository.save(customer);

    }

    @Override
    public List<Customer> findByExpireDate() {

        List<Customer> customerList = customerRepository.findAll();
        List<Customer> newCustomerList = new ArrayList<>();

        LocalDate now = LocalDate.now();

        for (Customer value : customerList){

            if (value.getMembershipExpireDate().isAfter(now)){
                newCustomerList.add(value);
            }

        }

        return newCustomerList;
    }

    @Override
    public List<Customer> findByMembershipNumber(String searchNumber) {

        List<Customer> customerList = customerRepository.findAll();
        List<Customer> customerListByMembershipNumber = customerRepository.findByMemberhsipNumber(searchNumber);

        return customerListByMembershipNumber;
    }

    @Override
    public List<Customer> findByName(String searchName) {
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> customerListByName = customerRepository.findByName(searchName);

        return customerListByName;
    }

    @Override
    public boolean checkExistingAccount(String membershipNumber) {

        Long totalCustomer = customerRepository.count(membershipNumber);

        return (totalCustomer > 0) ? true : false;
    }

    @Override
    public List<Customer> findBySearch(String searchName, String searchNumber) {

        List<Customer> customerListBySearch = customerRepository.findBySearch(searchName, searchNumber);

        return customerListBySearch;
    }

    public LocalDate extendExpireDate(LocalDate oldExpireDate){

        LocalDate newExpireDate = oldExpireDate.plusYears(2);
        return newExpireDate;

    }

    public LocalDate setExpireDate(){
        LocalDate now = LocalDate.now();

        LocalDate expireDate = now.plusYears(2);
        return expireDate;
    }


}
