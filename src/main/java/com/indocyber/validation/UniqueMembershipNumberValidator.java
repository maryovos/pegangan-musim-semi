package com.indocyber.validation;

import com.indocyber.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMembershipNumberValidator implements ConstraintValidator<UniqueMembershipNumber, String> {


@Autowired
private CustomerService customerService;

@Override
public boolean isValid(String membershipNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !customerService.checkExistingAccount(membershipNumber);
        }



}
