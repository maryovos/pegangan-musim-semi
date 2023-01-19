package com.indocyber.repository;

import com.indocyber.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {


    @Query("""
            SELECT COUNT(*)
            FROM Book AS b
            WHERE b.code = :bookCode
            """)
    public Long count(@Param("bookCode") String bookCode);



}
