package com.example.TrollMarket.service;

import com.example.TrollMarket.dto.AccountOptionDTO;
import com.example.TrollMarket.dto.account.*;
import com.example.TrollMarket.entitiy.Account;

import java.util.List;

public interface AccountService {
    void register(AccountRegisterDTO accountRegisterDTO);
    AccountTokenDTO createToken(AccountLoginDTO accountLoginDTO);
    List<AccountOptionDTO> getBuyerAllAccountOption();
    List<AccountOptionDTO> getSellerAllAccountOption();
    boolean checkExistingUsername(String username);


    AccountDTO findAccountByUsername(String username);
    void topUpBalance(AccountTopUp accountTopUp);

}
