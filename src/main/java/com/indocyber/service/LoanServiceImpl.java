package com.indocyber.service;

import com.indocyber.dto.LoanDto;
import com.indocyber.entity.Book;
import com.indocyber.entity.Customer;
import com.indocyber.entity.Loan;
import com.indocyber.repository.BookRepository;
import com.indocyber.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public void save(Book book, LoanDto loanDto) {

        Loan loan = new Loan();
        book.setBorrowed(true);
        bookRepository.save(book);

        loan.setCustomerNumber(loanDto.getCustomerNumber());
        loan.setBookCode(book);
        loan.setLoanDate(loanDto.getLoanDate());
        loan.setDueDate(getDueDate(loanDto.getLoanDate()));
        loan.setNote(loanDto.getNote());

        if (loanDto.getId() == null){
            loan.setId(loanDto.getId());
        }
        loanRepository.save(loan);

    }

    @Override
    public void save(Book book, LoanDto loanDto, Customer customer) {

        Loan loan = new Loan();
        book.setBorrowed(true);
        bookRepository.save(book);

        loan.setCustomerNumber(customer);
        loan.setBookCode(book);
        loan.setLoanDate(loanDto.getLoanDate());
        loan.setDueDate(getDueDate(loanDto.getLoanDate()));
        loan.setNote(loanDto.getNote());
        loanRepository.save(loan);

    }

    @Override
    public List<Loan> findAll() {

        List<Loan> loanList = loanRepository.findAll();

        return loanList;
    }

    @Override
    public Loan findById(Integer idLoan) {

        Optional<Loan> loanOptional = loanRepository.findById(idLoan);
        Loan tempLoan = null;

        if (loanOptional.isPresent()){
            tempLoan = loanOptional.get();
        }

        return tempLoan;
    }

    @Override
    public void updateReturnDate(Loan loan) {

        if (loan.getReturnDate() == null){
            LocalDate now = LocalDate.now();

            loan.setReturnDate(now);

            loanRepository.save(loan);
        }

    }

    @Override
    public List<Loan> findByCustomerName(String searchName) {

        List<Loan> loanList = loanRepository.findAll();
        List<Loan> loanByCustomerName = loanRepository.findByCustomerName(searchName);

        return loanByCustomerName;
    }

    @Override
    public List<Loan> findByBookTitle(String searchBookTitle) {

        List<Loan> loanByBookTitle = loanRepository.findByBookTitle(searchBookTitle);

        return loanByBookTitle;

    }

    @Override
    public List<Loan> findBySearch(String searchName, String searchBookTitle) {

        List<Loan> loanListBySearch = loanRepository.findBySearch(searchBookTitle, searchName);

        return loanListBySearch;
    }

    public LocalDate getDueDate(LocalDate loanDate){

        return loanDate.plusDays(5);

    }
}
