package com.indocyber.controller;

import com.indocyber.dto.customer.CustomerDto;
import com.indocyber.dto.customer.CustomerDtoUpdate;
import com.indocyber.entity.Customer;
import com.indocyber.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/index")
    public String index(Model model){

        List<Customer> customers = customerService.findAll();
//
        model.addAttribute("customers", customers);

        return "customer/index";
    }

    @RequestMapping("/addCustomer")
    public String addCustomer(Model model){

        Customer customer = new Customer();

        model.addAttribute("customer", customer);


        return "customer/addCustomer";
    }

    @PostMapping("/proccessCustomer")
    public String proccessCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto,
                                   BindingResult bindingResult,
                                   Model model){

        if (bindingResult.hasErrors()){

            return "customer/addCustomer";
        }

        customerService.save(customerDto);

        return "redirect:index";
    }

    @GetMapping("/editCustomer/{idCustomer}")
    public String editAuthor(@PathVariable("idCustomer") String idCustomer, Model model){


        Customer customer = customerService.findById(idCustomer);

        model.addAttribute("customer", customer);

        return "customer/editCustomer";

    }

    @PostMapping("/proccessUpdateCustomer")
    public String proccessUpdateCustomer(@Valid @ModelAttribute("customer") CustomerDtoUpdate customerDtoUpdate,
                                   BindingResult bindingResult,
                                   Model model){

        if (bindingResult.hasErrors()){

            return "customer/addCustomer";
        }

        Customer customer = customerService.findById(customerDtoUpdate.getMembershipNumber());

        customerService.update(customer, customerDtoUpdate);

        return "redirect:index";
    }


    @GetMapping("/deleteCustomer/{idCustomer}")
    public String deleteCustomer(@PathVariable("idCustomer") String idCustomer, Model model){


       customerService.deleteById(idCustomer);

        return "redirect:../index";

    }

    @GetMapping("/extendCustomer/{idCustomer}")
    public String extendCustomer(@PathVariable("idCustomer") String idCustomer, Model model){

        Customer customer = customerService.findById(idCustomer);


        customerService.extendExpireDateById(customer);

        return "redirect:../index";

    }

    @GetMapping("/detailCustomer/{idCustomer}")
    public String detailCustomer(@PathVariable("idCustomer") String idCustomer, Model model){

        Customer customer = customerService.findById(idCustomer);

        model.addAttribute("customer", customer);

        return "customer/detailCustomer";

    }

    @RequestMapping("/processSearching")
    public String processSearching(@RequestParam(defaultValue = "empty", name = "searchNumber") String searchNumber,
                                   @RequestParam(defaultValue = "empty", name = "searchName") String searchName,
                                   Model model){

        List<Customer> customers = new ArrayList<>();

        if (searchNumber.equals("empty") && searchName.equals("empty") ){
            customers = customerService.findAll();
        }
        if (!(searchNumber.equals("empty")) && (searchName.equals("empty")) ){

            customers = customerService.findByMembershipNumber(searchNumber);
        }
        if ((searchNumber.equals("empty")) && !(searchName.equals("empty")) ){
            customers = customerService.findByName(searchName);
        }
        if (!(searchNumber.equals("empty")) && !(searchName.equals("empty")) ){
            customers = customerService.findBySearch(searchName, searchNumber);
        }


        model.addAttribute("customers", customers);

        return "customer/index";

    }


}
