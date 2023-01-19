package com.indocyber.repository;

import com.indocyber.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("""
            SELECT COUNT(*)
            FROM Category AS cat
            WHERE cat.name = :categoryName
            """)
    public Long count(@Param("categoryName") String categoryName);

    @Query("""
            SELECT cat
            FROM Category AS cat
            WHERE cat.name LIKE %:categoryName%
            """)
    List<Category> findBySearch(@Param("categoryName")String search);
}
