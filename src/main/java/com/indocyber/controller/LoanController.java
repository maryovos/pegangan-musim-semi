package com.indocyber.controller;

import com.indocyber.dto.LoanDto;
import com.indocyber.entity.*;
import com.indocyber.service.BookService;
import com.indocyber.service.CustomerService;
import com.indocyber.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/index")
    public String index(Model model){

        List<Loan> loanList = loanService.findAll();

        model.addAttribute("loanList", loanList);
        return "loan/index";
    }

    @RequestMapping("/addLoan")
    public String addLoan(Model model){

        Loan loan = new Loan();
        List<Customer> customerList = customerService.findByExpireDate();
        List<Book> bookList = bookService.findByBorrowed();

        model.addAttribute("loan", loan);
        model.addAttribute("customerList", customerList);
        model.addAttribute("bookList", bookList);

        return "loan/addLoan";

    }

    @RequestMapping("/proccessLoan")
    public String proccessLoan(@Valid @ModelAttribute("loan") LoanDto loanDto,
                               BindingResult bindingResult,
                               Model model){


        if (bindingResult.hasErrors()){
            List<Customer> customerList = customerService.findByExpireDate();
            List<Book> bookList = bookService.findByBorrowed();

            model.addAttribute("customerList", customerList);
            model.addAttribute("bookList", bookList);
            return "loan/addLoan";
        }


        Book book = bookService.findById(loanDto.getBookCode().getCode());
        book.setBorrowed(true);

        loanService.save(book, loanDto);

        return "redirect:index";

    }

    @GetMapping("/returnBook/{idLoan}")
    public String returnBook (@PathVariable("idLoan") Integer idLoan){


        Loan loan = loanService.findById(idLoan);
        loanService.updateReturnDate(loan);

        Book book = bookService.findById(loan.getBookCode().getCode());
        book.setBorrowed(false);
        bookService.save(book);

        return "redirect:../index";
    }

    @GetMapping("/editBook/{idLoan}")
    public String editBook (@PathVariable("idLoan") Integer idLoan, Model model){

        System.out.println("Berhasil masuk");

        Loan loan = loanService.findById(idLoan);
        List<Customer> customerList = customerService.findByExpireDate();
        List<Book> bookList = bookService.findByBorrowed();

        model.addAttribute("loan", loan);
        model.addAttribute("customerList", customerList);
        model.addAttribute("bookList", bookList);

        System.out.println(loan.getBookCode().getTitle());

        return "loan/editLoan";
    }

    @RequestMapping("/proccessUpdateLoan")
    public String proccessUpdateLoan(@Valid @ModelAttribute("loan") LoanDto loanDto,
                               BindingResult bindingResult,
                               Model model){


        if (bindingResult.hasErrors()){
            List<Customer> customerList = customerService.findByExpireDate();
            List<Book> bookList = bookService.findByBorrowed();

            model.addAttribute("customerList", customerList);
            model.addAttribute("bookList", bookList);
            return "loan/editLoan";
        }

        System.out.println(loanDto.getId());

        Book book = bookService.findById(loanDto.getBookCode().getCode());
        book.setBorrowed(true);

        loanService.save(book, loanDto);

        return "redirect:index";

    }


    @RequestMapping("/processSearching")
    public String processSearching(@RequestParam(defaultValue = "empty", name = "searchBookTitle") String searchBookTitle,
                                   @RequestParam(defaultValue = "empty", name = "searchName") String searchName,
                                   Model model){

        List<Loan> loanList = new ArrayList<>();
//
        if (searchName.equals("empty") && searchBookTitle.equals("empty") ){
            loanList = loanService.findAll();
        }
        if (!(searchName.equals("empty")) && (searchBookTitle.equals("empty")) ){
            loanList = loanService.findByCustomerName(searchName);
        }
        if ((searchName.equals("empty")) && !(searchBookTitle.equals("empty")) ){
            loanList = loanService.findByBookTitle(searchBookTitle);
        }
        if (!(searchName.equals("empty")) && !(searchBookTitle.equals("empty")) ){
            loanList = loanService.findBySearch(searchName, searchBookTitle);
        }



        model.addAttribute("loanList", loanList);
        return "loan/index";

    }

    @GetMapping("/detailBook/{idLoan}")
    public String detailBook (@PathVariable("idLoan") Integer idLoan, Model model){

        Loan loan = loanService.findById(idLoan);
        Customer customer = loan.getCustomerNumber();
        Book book = loan.getBookCode();
        Category category = book.getCategoryName();
        Author author = book.getAuthor();

        model.addAttribute("customer", customer);
        model.addAttribute("book", book);
        model.addAttribute("loan", loan);
        model.addAttribute("category", category);
        model.addAttribute("author", author);


        return "loan/detail";
    }




}
