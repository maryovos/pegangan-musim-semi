package com.indocyber.repository;

import com.indocyber.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {


    @Query("""
            SELECT COUNT(*)
            FROM Customer AS cus
            WHERE cus.membershipNumber = :membershipNumber
            """)

    public Long count(@Param("membershipNumber") String membershipNumber);

    @Query("""
            SELECT cus
            FROM Customer AS cus
            WHERE cus.membershipNumber LIKE %:membershipNumber%
            """)
    List<Customer> findByMemberhsipNumber(@Param("membershipNumber") String searchNumber);

    @Query("""
            SELECT cus
            FROM Customer AS cus
            WHERE cus.firstName LIKE %:name% OR cus.lastName LIKE %:name%
            """)
    List<Customer> findByName(@Param("name") String searchName);


    @Query("""
            SELECT cus FROM Customer AS cus
            WHERE cus.membershipNumber LIKE %:membershipNumber% AND
            (cus.firstName LIKE %:name% OR cus.lastName LIKE %:name%)
            """)
    List<Customer> findBySearch( @Param("name") String searchName, @Param("membershipNumber") String searchNumber);
}
