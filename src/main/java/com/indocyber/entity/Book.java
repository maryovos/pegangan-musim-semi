package com.indocyber.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "Code")
    private String code;

    @Column(name = "Title")
    private String title;

    @Column(name = "IsBorrowed")
    private Boolean isBorrowed;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Category_Id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Category categoryName;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Author_Id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Author author;

    @Column(name = "Summary")
    private String summary;

    @Column(name = "ReleaseDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Column(name = "TotalPage")
    private Integer totalPage;

    @JsonIgnore
    @OneToMany(
            mappedBy = "bookCode",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Loan> loanList;

    public Book(){}

    public Book(String code, String title, Boolean isBorrowed, Category categoryName, Author author, String summary, LocalDate releaseDate, Integer totalPage) {
        this.code = code;
        this.title = title;
        this.isBorrowed = isBorrowed;
        this.categoryName = categoryName;
        this.author = author;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getBorrowed() {

        return isBorrowed;
    }

    public String getBorrowedString() {

        if (this.isBorrowed.booleanValue() == true){
            return "Borrowed";
        }


        return "Available";
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }

    public Category getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDateString(){

        if (this.releaseDate == null){
            return "-";
        }

        Locale indonesia = new Locale("id", "ID");
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy", indonesia);

        return indoFormat.format(this.releaseDate);
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }
}
