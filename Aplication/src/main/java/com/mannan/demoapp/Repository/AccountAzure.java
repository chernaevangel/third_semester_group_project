package com.mannan.demoapp.Repository;

import com.mannan.demoapp.Model.AccountPackage.Account;
import com.mannan.demoapp.Model.AccountPackage.AccountRequest;
import com.mannan.demoapp.Repository.AzureConn.DefaultCon;
import com.mannan.demoapp.Repository.AzureConn.DefaultImage;
import com.mannan.demoapp.Repository.Interfaces.IAccountAzure;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountAzure implements IAccountAzure {

    DefaultCon con = new DefaultCon();
    DefaultImage img = new DefaultImage();

    public AccountAzure() throws SQLException {
    }

    @Override
    public List<Account> findAll() throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            List<Account> accounts = new ArrayList<>();
            PreparedStatement selectSql = connection.prepareStatement("SELECT *  from Account WHERE Visibility != 0");
            ResultSet result = selectSql.executeQuery();
            while (result.next()) {
                Account newAcc =
                        new Account(result.getLong(4),
                                result.getString(6),
                                result.getString(3),
                                result.getString(2),
                                result.getInt(5),
                                result.getString(7));
                accounts.add(newAcc);
            }
            connection.close();
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return null;
    }

    @Override
    public Account findByPcn(Long pcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            Account newAcc = null;
            PreparedStatement selectSql = connection.prepareStatement("SELECT * FROM Account WHERE PCN = ?");
            selectSql.setLong(1, pcn);
            ResultSet result = selectSql.executeQuery();
            while (result.next()) {
                newAcc = new Account(result.getLong(4),
                        result.getString(6),
                        result.getString(3),
                        result.getString(2),
                        result.getInt(5),
                        result.getString(7));
            }
            connection.close();
            return newAcc;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return null;
    }

    @Override
    public boolean delete(Long pcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("DELETE FROM Account WHERE PCN = ?");
            selectSql.setLong(1, pcn);
            selectSql.executeUpdate();

            PreparedStatement selectSql1 = connection.prepareStatement("DELETE FROM Chat WHERE PCN1 = ? OR PCN2 = ?");
            selectSql1.setLong(1, pcn);
            selectSql1.setLong(2, pcn);
            selectSql.executeUpdate();

            PreparedStatement selectSql2 = connection.prepareStatement("DELETE FROM Connection WHERE Pcn1 = ? OR Pcn2 = ?");
            selectSql2.setLong(1, pcn);
            selectSql2.setLong(2, pcn);
            selectSql.executeUpdate();

            PreparedStatement selectSql3 = connection.prepareStatement("DELETE FROM Experience WHERE AccountPcn = ?");
            selectSql3.setLong(1, pcn);
            selectSql.executeUpdate();

            PreparedStatement selectSql4 = connection.prepareStatement("DELETE FROM Interest WHERE PCN = ?");
            selectSql4.setLong(1, pcn);
            selectSql.executeUpdate();

            PreparedStatement selectSql5 = connection.prepareStatement("DELETE FROM Message WHERE SenderPCN = ?");
            selectSql5.setLong(1, pcn);
            selectSql.executeUpdate();

            PreparedStatement selectSql6 = connection.prepareStatement("DELETE FROM Post WHERE AccountPCN = ?");
            selectSql6.setLong(1, pcn);
            selectSql.executeUpdate();

            PreparedStatement selectSql7 = connection.prepareStatement("DELETE FROM Project WHERE AccountPcn = ?");
            selectSql7.setLong(1, pcn);
            selectSql.executeUpdate();

            PreparedStatement selectSql8 = connection.prepareStatement("DELETE FROM Skill WHERE AccountPcn = ?");
            selectSql8.setLong(1, pcn);
            selectSql.executeUpdate();

            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return false;
    }

    @Override
    public boolean update(Account account) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("UPDATE Account SET AcademicType = ?, Bio = ?, Name = ?, Visibility = ? WHERE PCN = ?");
            selectSql.setString(1, account.getType());
            selectSql.setString(2, account.getBio());
            selectSql.setString(3, account.getName());
            selectSql.setLong(5, account.getPcn());
            selectSql.setLong(4, account.getVisibility());
            selectSql.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return false;
    }


    @Override
    public boolean updatePicture(Account account) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("UPDATE Account SET Image = ? WHERE PCN = ?");
            selectSql.setString(1, account.getBinaryImage());
            selectSql.setLong(2, account.getPcn());
            selectSql.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return false;
    }

    @Override
    public boolean create(Account account) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("INSERT INTO Account (AcademicType, Bio, PCN, Name, Visibility, Image) VALUES (?,?,?,?, 0, ?)");
            selectSql.setString(1, account.getType());
            selectSql.setString(2, account.getBio());
            selectSql.setLong(3, account.getPcn());
            selectSql.setString(4, account.getName());
            selectSql.setString(5, img.getImage());
            selectSql.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return false;
    }

    @Override
    public AccountRequest viewAccount(Long pcn, Long myPcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            AccountRequest request = new AccountRequest();
            request.setPcn2(myPcn);
            PreparedStatement selectSql = connection.prepareStatement("select a.pcn, a.visibility from account as a" +
                    " where a.PCN = ?");
            selectSql.setLong(1, pcn);
            ResultSet result = selectSql.executeQuery();
            while (result.next()) {
                request.setPcn1(pcn);
                request.setVisibility(result.getInt(2));
            }

            PreparedStatement selectSql2 = connection.prepareStatement("select c.accepted from connection as c " +
                    "where (Pcn1 = ? OR Pcn1 = ?) AND (Pcn2 = ? OR Pcn2 = ?)");
            selectSql2.setLong(1, pcn);
            selectSql2.setLong(2, myPcn);
            selectSql2.setLong(3, pcn);
            selectSql2.setLong(4, myPcn);
            ResultSet result2 = selectSql2.executeQuery();
            if (!result2.next()) {
                request.setAccepted(0);
                connection.close();
                return  request;
            }
                else {
                    request.setAccepted(result2.getInt(1));
                }
            connection.close();
            return request;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return null;
    }
}

