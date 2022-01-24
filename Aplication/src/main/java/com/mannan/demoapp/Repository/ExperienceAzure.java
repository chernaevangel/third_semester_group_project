package com.mannan.demoapp.Repository;

import com.mannan.demoapp.Model.AccountPackage.Experience;
import com.mannan.demoapp.Repository.AzureConn.DefaultCon;
import com.mannan.demoapp.Repository.Interfaces.IExperienceAzure;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExperienceAzure implements IExperienceAzure {

    public ExperienceAzure() throws SQLException {}
    DefaultCon con = new DefaultCon();

    @Override
    public List<Experience> findAllByPcn(Long pcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            List<Experience> experiences = new ArrayList<>();
            PreparedStatement selectSql = connection.prepareStatement(
                    "select * from Experience as e where e.[AccountPcn] = ?");
            selectSql.setLong(1, pcn);
            ResultSet result = selectSql.executeQuery();
            while(result.next())
            {
                experiences.add(new Experience(result.getLong(1),result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getLong(6)));
            }
            connection.close();
            return experiences;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }

    @Override
    public boolean create(Experience experience) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement(
                    "INSERT INTO [dbo].[Experience]" +
                            "([Type], [Duration], [Description], [Institure], [AccountPcn])" +
                            "VALUES (?, ?, ?, ?, ?)");
            selectSql.setString(1, experience.getType());
            selectSql.setString(2, experience.getDuration());
            selectSql.setString(3, experience.getDescription());
            selectSql.setString(4, experience.getInstitute());
            selectSql.setLong(5, experience.getAccountPcn());
            selectSql.executeQuery();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }


    @Override
    public boolean update(Experience experience) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("UPDATE [dbo].[Experience]" +
                    "SET" +
                    "    [Type] = ?," +
                    "    [Duration] = ?," +
                    "    [Description] = ?," +
                    "    [Institure] = ?" +
                    " WHERE Id = ?");
            selectSql.setString(1, experience.getType());
            selectSql.setString(2, experience.getDuration());
            selectSql.setString(3, experience.getDescription());
            selectSql.setString(4, experience.getInstitute());
            selectSql.setLong(5, experience.getId());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public boolean delete(Experience experience) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("DELETE FROM Experience WHERE Id = ?");
            selectSql.setLong(1, experience.getId());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }
}
