package com.mannan.demoapp.Manager;

import com.mannan.demoapp.Interfaces.IAccountManager;
import com.mannan.demoapp.Model.AccountPackage.Account;
import com.mannan.demoapp.Model.AccountPackage.AccountRequest;
import com.mannan.demoapp.Repository.Interfaces.IAccountAzure;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class AccountManager implements IAccountManager {

    private IAccountAzure dataClass;

    public AccountManager(IAccountAzure dataClass){
        this.dataClass=dataClass;
    }


    @Override
    public List<Account> getAccounts() throws SQLException {
        return dataClass.findAll();
    }

    @Override
    public Account getAccountByPcn(Long pcn) {
        try {
            Account account = dataClass.findByPcn(pcn);
            if (account != null) {
                return account;
            } else {
                account = new Account(pcn, "", "", "Student", 0, "");
                addAccount(account);
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    public boolean deleteAccount(Long pcn) {
        try {
            return dataClass.delete(pcn);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean addAccount(Account account) {
        try {
            return dataClass.create(account);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean updateAccount(Account account) {
        try {
            return dataClass.update(account);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean updatePicture(Account account) {
        try {
            return dataClass.updatePicture(account);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public Account viewAccount(Long pcn, Long myPcn) {
        try {
            AccountRequest request = dataClass.viewAccount(pcn, myPcn);
            if (request != null) {
                if (request.getVisibility() == 0) {
                    return null;
                } else if (request.getVisibility() == 1 && request.getAccepted() == 1) {
                    return getAccountByPcn(request.getPcn1());
                } else if (request.getVisibility() == 2) {
                    return getAccountByPcn(request.getPcn1());
                }

            }
            System.out.println(request.getVisibility() + "  " + request.getAccepted());
            return null;
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public List<Account> searchAccount(String name) throws SQLException {
        try {
            List<Account> accounts = getAccounts();
            accounts.removeIf(account -> !account.getName().contains(name));
            return accounts;
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
        }
    }

