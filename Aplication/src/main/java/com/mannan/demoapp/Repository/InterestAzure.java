package com.mannan.demoapp.Repository;

import com.mannan.demoapp.Model.AccountPackage.Interest;
import com.mannan.demoapp.Repository.AzureConn.DefaultCon;
import com.mannan.demoapp.Repository.Interfaces.IInterestAzure;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InterestAzure implements IInterestAzure {

    public InterestAzure() throws SQLException {}
    DefaultCon con = new DefaultCon();

    @Override
    public List<Interest> findAllByPcn(Long pcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
       try {
           List<Interest> interests = new ArrayList<>();
           PreparedStatement selectSql = connection.prepareStatement(
                   "select * from Interest as i where i.[PCN] = ?");
           selectSql.setLong(1, pcn);
           ResultSet result = selectSql.executeQuery();
           while(result.next())
           {
               interests.add(new Interest(result.getLong(1),result.getString(2), result.getLong(3)));
           }
           connection.close();
           return interests;
       }
       catch (SQLException e) {e.printStackTrace();}
       connection.close();
       return null;
    }

    @Override
    public boolean delete(Interest interest) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("DELETE FROM [dbo].[Interest]" +
                    "WHERE Id = ?");
            selectSql.setLong(1, interest.getId());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public boolean create(Interest interest) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement(
                    "INSERT INTO [dbo].[Interest]" +
                            "([Type], [PCN])" +
                            "VALUES (?, ?)");
            selectSql.setString(1, interest.getInterest());
            selectSql.setLong(2, interest.getAccountPCN());
            selectSql.executeQuery();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }
}

