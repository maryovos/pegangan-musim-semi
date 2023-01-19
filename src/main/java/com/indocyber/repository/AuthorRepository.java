package com.indocyber.repository;

import com.indocyber.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {


    @Query("""
            SELECT a
            FROM Author AS a
            WHERE a.firstName LIKE %:name% OR a.lastName LIKE %:name%
            """)
    List<Author> findBySearch(@Param("name") String search);


    @Query("""
            SELECT a
            FROM Author AS a
            """)
    List<Author> findAll(@Param("name") String name, Pageable pageable);

    @Query("""
			SELECT COUNT(aut)
			FROM Author AS aut
			""")
    public Long count(@Param("name") String name);

    @Query("""
            SELECT a
            FROM Author AS a
            """)
    Page<Author> findByPage(Pageable pagination);
}
