package com.indocyber.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "MembershipNumber")
    private String membershipNumber;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "BirthDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Address")
    private String address;

    @Column(name = "MembershipExpireDate")
    private LocalDate membershipExpireDate;

    @JsonIgnore
    @OneToMany(
            mappedBy = "customerNumber",
            fetch = FetchType.EAGER
    )
    private List<Loan> loanList;

    public Customer() {}

    public Customer(String membershipNumber, String firstName, String lastName, LocalDate birthDate, String gender, String phone, String address, LocalDate membershipExpireDate) {
        this.membershipNumber = membershipNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.membershipExpireDate = membershipExpireDate;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getMembershipExpireDate() {
        return membershipExpireDate;
    }

    public String getMembershipExpireDateString() {
        Locale indonesia = new Locale("id", "ID");
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy", indonesia);

        return indoFormat.format(this.membershipExpireDate);
    }

    public String getBirthDateString(){
        Locale indonesia = new Locale("id", "ID");
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy", indonesia);

        return indoFormat.format(this.birthDate);
    }
    public void setMembershipExpireDate(LocalDate membershipExpireDate) {
        this.membershipExpireDate = membershipExpireDate;
    }

    public String getFullName(){

        return this.firstName+ " " + this.lastName;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }
}
