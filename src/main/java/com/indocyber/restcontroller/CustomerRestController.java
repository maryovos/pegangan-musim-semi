package com.indocyber.restcontroller;

import com.indocyber.dto.customer.CustomerDto;
import com.indocyber.dto.customer.CustomerDtoUpdate;
import com.indocyber.entity.Customer;
import com.indocyber.entity.Loan;
import com.indocyber.exceptionhandling.ObjectNotFound;
import com.indocyber.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerRestController {


    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomer(){

        List<Customer> customerList = customerService.findAll();

        return new ResponseEntity<>(customerList, HttpStatus.FOUND);

    }

    @GetMapping("{idCustomer}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("idCustomer") String idCustomer){

        Customer customer = customerService.findById(idCustomer);

        if (customer == null){
            throw new ObjectNotFound("Customer With ID: " + idCustomer + " not found!!!");
        }

        return new ResponseEntity<>(customer, HttpStatus.FOUND);

    }

    @GetMapping("{idCustomer}/loan")
    public ResponseEntity<List<Loan>> getLoanByCustomer(@PathVariable("idCustomer") String idCustomer){

        Customer customer = customerService.findById(idCustomer);

        if (customer == null){
            throw new ObjectNotFound("Customer With ID: " + idCustomer + " not found!!!");
        }

        return new ResponseEntity<>(customer.getLoanList(), HttpStatus.FOUND);

    }

    @PostMapping()
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDto customerDto){

        customerService.save(customerDto);

        return new ResponseEntity<>("customer has been created", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{idCustomer}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("idCustomer") String idCustomer){

        Customer customer = customerService.findById(idCustomer);

        if (customer == null){
            throw new ObjectNotFound("Customer With ID: " + idCustomer + " not found!!!");
        }

        customerService.deleteById(idCustomer);

        return new ResponseEntity<>("customer has been deleted", HttpStatus.OK);
    }

    @PutMapping("{idCustomer}")
    public ResponseEntity<String> editCustomerById(@Valid @RequestBody CustomerDtoUpdate customerDtoUpdate,
                                                  @PathVariable("idCustomer") String idCustomer){

        Customer customerById = customerService.findById(idCustomer);

        if (customerById == null){
            throw new ObjectNotFound("Customer With ID: " + idCustomer + " Not Found!!");
        }

        Customer customer = customerService.findById(customerDtoUpdate.getMembershipNumber());

        customerService.update(customer, customerDtoUpdate);

        return new ResponseEntity<>("Data Customer berhasil diubah", HttpStatus.FOUND);
    }


}
