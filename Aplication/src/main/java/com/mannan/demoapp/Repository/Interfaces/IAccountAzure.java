package com.mannan.demoapp.Repository.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Account;
import com.mannan.demoapp.Model.AccountPackage.AccountRequest;

import java.sql.SQLException;
import java.util.List;

public interface IAccountAzure {
    List<Account> findAll() throws SQLException;
    Account findByPcn(Long pcn) throws SQLException;
    boolean update(Account acc) throws SQLException;
    boolean updatePicture(Account acc) throws SQLException;
    boolean create(Account acc) throws SQLException;
    boolean delete(Long id) throws SQLException;
    AccountRequest viewAccount(Long pcn, Long myPcn) throws SQLException;
}
