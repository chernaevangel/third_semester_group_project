package com.mannan.demoapp;


import com.mannan.demoapp.Manager.AccountManager;
import com.mannan.demoapp.Model.AccountPackage.Account;
import com.mannan.demoapp.Repository.Interfaces.IAccountAzure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class AccountServiceTests {

    @Mock
    private IAccountAzure dataClass;
    AccountManager accountManager;

    @BeforeEach
    void beforeEach() {
        accountManager = new AccountManager(dataClass);
    }

    @Test
    void getAccounts() throws SQLException {
        accountManager.getAccounts();

        verify(dataClass).findAll();

    }

    @Test
    void getAccountByPcn() throws SQLException {
        accountManager.getAccountByPcn(1234L);

        verify(dataClass).findByPcn(1234L);
    }

    @Test
    void deleteAccount() throws SQLException {
        accountManager.deleteAccount(1234L);

        verify(dataClass).delete(1234L);
    }

    @Test
    void addAccount() throws SQLException {
        Account acc = new Account(1234L, "Jo Test", "Bio", "Student", 1, null);

        accountManager.addAccount(acc);

        verify(dataClass).create(acc);
    }

    @Test
    void viewAccount() throws SQLException {
        accountManager.viewAccount(1234L, 2345L);

        verify(dataClass).viewAccount(1234L, 2345L);
    }
}
