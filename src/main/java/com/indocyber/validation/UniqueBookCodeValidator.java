package com.indocyber.validation;

import com.indocyber.service.BookService;
import com.indocyber.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueBookCodeValidator implements ConstraintValidator<UniqueBookCode, String> {


    @Autowired
    private BookService bookService;


    @Override
    public boolean isValid(String bookCode, ConstraintValidatorContext constraintValidatorContext) {
        return !bookService.checkExistingAccount(bookCode);
    }
}
