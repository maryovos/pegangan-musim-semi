package com.indocyber.service;

import com.indocyber.dto.LoanDto;
import com.indocyber.entity.Book;
import com.indocyber.entity.Customer;
import com.indocyber.entity.Loan;

import java.util.List;

public interface LoanService {
    void save(Book book, LoanDto loanDto);

    void save(Book book, LoanDto loanDto, Customer customer);

    List<Loan> findAll();

    Loan findById(Integer idLoan);

    void updateReturnDate(Loan loan);

    List<Loan> findByCustomerName(String searchName);

    List<Loan> findByBookTitle(String searchBookTitle);

    List<Loan> findBySearch(String searchName, String searchBookTitle);
}
