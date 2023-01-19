package com.indocyber.service;

import com.indocyber.dto.category.CategoryDto;
import com.indocyber.dto.category.CategoryDtoUpdate;
import com.indocyber.entity.Category;
import com.indocyber.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(CategoryDto categoryDto) {

        Category category = new Category();

        category.setName(categoryDto.getName().toUpperCase());
        category.setFloor(categoryDto.getFloor());
        category.setIsle(categoryDto.getIsle().toUpperCase());
        category.setBay(categoryDto.getBay());

        categoryRepository.save(category);

    }

    @Override
    public void save(CategoryDtoUpdate categoryDtoUpdate) {
        Category category = new Category();

        category.setName(categoryDtoUpdate.getName().toUpperCase());
        category.setFloor(categoryDtoUpdate.getFloor());
        category.setIsle(categoryDtoUpdate.getIsle().toUpperCase());
        category.setBay(categoryDtoUpdate.getBay());

        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {

        List<Category> categoryList = categoryRepository.findAll();

        return categoryList;
    }

    @Override
    public Category findById(String nameCategory) {

        Optional<Category> categoryOptional = categoryRepository.findById(nameCategory);
        Category tempCategory = null;

        if (categoryOptional.isPresent()){

            tempCategory = categoryOptional.get();

        }

        return tempCategory;
    }

    @Override
    public void deleteById(String categoryName) {

        categoryRepository.deleteById(categoryName);

    }

    @Override
    public List<Category> findByName(String search) {

        List<Category> categoryListBySearch = categoryRepository.findBySearch(search);

        return categoryListBySearch;
    }

    @Override
    public boolean checkExistingAccount(String categoryName) {

        Long totalCategoryName = categoryRepository.count(categoryName);

        return  (totalCategoryName > 0) ? true : false;
    }
}
