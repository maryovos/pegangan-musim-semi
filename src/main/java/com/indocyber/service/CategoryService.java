package com.indocyber.service;

import com.indocyber.dto.category.CategoryDto;
import com.indocyber.dto.category.CategoryDtoUpdate;
import com.indocyber.entity.Category;

import java.util.List;

public interface CategoryService {
    void save(CategoryDto categoryDto);

    void save(CategoryDtoUpdate categoryDtoUpdate);

    List<Category> findAll();

    Category findById(String nameCategory);

    void deleteById(String categoryName);

    List<Category> findByName(String search);

    boolean checkExistingAccount(String categoryName);
}
