package com.indocyber.service;

import com.indocyber.ApplicationUserDetails;
import com.indocyber.dto.RegisterDto;
import com.indocyber.entity.Account;
import com.indocyber.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {


    @Autowired
    private AccountRepository accountRepository;

    // Get PasswordEncoder
    // PasswordEncoder passwordEncoder = MvcSecurityConfiguration.passwordEncoder();
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void registerAccount(RegisterDto dto) {

        // Encode password
        String hashPassword = passwordEncoder.encode(dto.getPassword());

        // Initialize account
        Account account = new Account(
                dto.getUsername(),
                hashPassword,
                dto.getRole());

        // save the account
        accountRepository.save(account);

    }

    @Override
    public String getAccountRole(String username) {
        Optional<Account> accountOptional = accountRepository.findById(username);

        Account tempAccount = null;
        if (accountOptional.isPresent()) {
            tempAccount = accountOptional.get();
        }

        return tempAccount.getRole();
    }

    @Override
    public Boolean checkExistingAccount(String username) {
        Long totalUser = accountRepository.count(username);

        return totalUser > 0 ? true : false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);

        Optional<Account> optionalAccount = accountRepository.findById(username);

        Account tempAccount = null;
        if (optionalAccount.isPresent()) {
            tempAccount = optionalAccount.get();
        }

        return new ApplicationUserDetails(tempAccount);

    }
}
