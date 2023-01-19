package com.indocyber.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CustomerNumber")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Customer customerNumber;

    @ManyToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "BookCode")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Book bookCode;

    @Column(name = "LoanDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanDate;

    @Column(name = "DueDate")
    private LocalDate dueDate;

    @Column(name = "ReturnDate")
    private LocalDate returnDate;

    @Column(name = "Note")
    private String note;

    public Loan(){}

    public Loan(Customer customerNumber, Book bookCode, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, String note) {
        this.customerNumber = customerNumber;
        this.bookCode = bookCode;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Customer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Book getBookCode() {
        return bookCode;
    }

    public void setBookCode(Book bookCode) {
        this.bookCode = bookCode;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public String getLoanDateString(){

        Locale indonesia = new Locale("id", "ID");
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy", indonesia);

        return indoFormat.format(this.loanDate);

    }

    public String getDueDateString(){

        Locale indonesia = new Locale("id", "ID");
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy", indonesia);

        return indoFormat.format(this.dueDate);

    }

    public String getReturnDateString(){

        if (this.returnDate == null){
            return "-";
        }

        Locale indonesia = new Locale("id", "ID");
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy", indonesia);

        return indoFormat.format(this.returnDate);

    }

}
