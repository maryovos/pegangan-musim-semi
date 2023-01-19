package com.indocyber.service;

import com.indocyber.dto.RegisterDto;

public interface AccountService {

    void registerAccount(RegisterDto dto);
    String getAccountRole(String username);
    Boolean checkExistingAccount(String username);



}
