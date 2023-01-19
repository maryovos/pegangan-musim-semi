package com.indocyber.validation;

import com.indocyber.service.CategoryService;
import com.indocyber.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {


    @Autowired
    private CategoryService categoryService;


    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryService.checkExistingAccount(categoryName);
    }
}
