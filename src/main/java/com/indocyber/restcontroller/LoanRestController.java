package com.indocyber.restcontroller;

import com.indocyber.dto.AuthorDto;
import com.indocyber.dto.LoanDto;
import com.indocyber.entity.Author;
import com.indocyber.entity.Book;
import com.indocyber.entity.Customer;
import com.indocyber.entity.Loan;
import com.indocyber.exceptionhandling.ObjectNotFound;
import com.indocyber.service.BookService;
import com.indocyber.service.CustomerService;
import com.indocyber.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/loan")
public class LoanRestController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;


    @GetMapping()
    public ResponseEntity<List<Loan>> getAllBook(){

        List<Loan> loanList = loanService.findAll();

        return new ResponseEntity<>(loanList, HttpStatus.FOUND);
    }

    @GetMapping("{idLoan}")
    public ResponseEntity<Loan> getBookById(@PathVariable("idLoan") Integer idLoan){

        Loan loan = loanService.findById(idLoan);

        if (loan == null){
            throw new ObjectNotFound("Loan With ID: " + idLoan + " not found!!!");
        }


        return new ResponseEntity<>(loan, HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> addLoan(@Valid @RequestBody LoanDto loanDto){

        Book book = bookService.findById(loanDto.getBookCode().getCode());
        Customer customer = customerService.findById(loanDto.getCustomerNumber().getMembershipNumber());


        if (book.getBorrowed().booleanValue() == true){
            throw new ObjectNotFound("Code Book With ID: " + loanDto.getBookCode().getCode() + " not available!!!");
        }

        if (book == null){
            throw new ObjectNotFound("Code Book With ID: " + loanDto.getBookCode().getCode() + " not found!!!");
        }

        if (customer == null){
            throw new ObjectNotFound("Customer Book With ID: " + loanDto.getCustomerNumber().getMembershipNumber() + " not found!!!");
        }



        loanService.save(book, loanDto, customer);

        return new ResponseEntity<>("loan has been created", HttpStatus.ACCEPTED);
    }


    @GetMapping("{idLoan}/returnBook")
    public ResponseEntity<String> returnBookById(@PathVariable("idLoan") Integer idLoan){

        Loan loan = loanService.findById(idLoan);

        if (loan == null){
            throw new ObjectNotFound("Loan With ID: " + idLoan + " not found!!!");
        }

        if (loan.getReturnDate() != null){
            throw new ObjectNotFound("Loan With ID: " + idLoan + " has return!!!");
        }


        loanService.updateReturnDate(loan);

        Book book = bookService.findById(loan.getBookCode().getCode());
        book.setBorrowed(false);
        bookService.save(book);


        return new ResponseEntity<>("Book has return", HttpStatus.FOUND);
    }



}
