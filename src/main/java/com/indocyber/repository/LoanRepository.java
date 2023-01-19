package com.indocyber.repository;

import com.indocyber.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {


    @Query("""
             SELECT l FROM Loan AS l
            JOIN l.customerNumber c
            JOIN l.bookCode b
            WHERE b.title LIKE %:title%
            """)
    List<Loan> findByBookTitle(@Param("title") String searchBookTitle);

    @Query("""
            SELECT l FROM Loan AS l
            JOIN l.customerNumber c
            JOIN l.bookCode b
            WHERE c.firstName LIKE %:customerName% OR c.lastName LIKE %:customerName%
           """)
    List<Loan> findByCustomerName(@Param("customerName") String searchName);

    @Query("""
            SELECT l FROM Loan AS l
            JOIN l.customerNumber c
            JOIN l.bookCode b
            WHERE b.title LIKE %:title% AND 
            (c.firstName LIKE %:customerName% OR c.lastName LIKE %:customerName%)
            """)
    List<Loan> findBySearch(@Param("title") String searchBookTitle, @Param("customerName") String searchName );
}
