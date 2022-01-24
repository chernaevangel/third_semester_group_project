package com.mannan.demoapp.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountManager {
    List<Account> getAccounts() throws SQLException;
    Account getAccountByPcn(Long pcn);
    boolean deleteAccount(Long pcn);
    boolean addAccount(Account acc);
    boolean updateAccount(Account acc);
    boolean updatePicture(Account acc);
    Account viewAccount(Long pcn, Long myPcn);
    List<Account> searchAccount(String name) throws SQLException;
}
