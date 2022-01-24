package com.mannan.demoapp.Repository;

import com.mannan.demoapp.Model.AccountPackage.Skill;
import com.mannan.demoapp.Repository.AzureConn.DefaultCon;
import com.mannan.demoapp.Repository.Interfaces.ISkillAzure;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SkillAzure implements ISkillAzure {

    public SkillAzure() throws SQLException {}
    DefaultCon con = new DefaultCon();

    @Override
    public List<Skill> findAllByPcn(Long pcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            List<Skill> skills = new ArrayList<>();
            PreparedStatement selectSql = connection.prepareStatement(
                    "select * from Skill as i where i.[AccountPcn] = ?");
            selectSql.setLong(1, pcn);
            ResultSet result = selectSql.executeQuery();
            while(result.next())
            {
                skills.add(new Skill(result.getLong(1),result.getString(2), result.getString(4), result.getLong(3)));
            }
            connection.close();
            return skills;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }

    @Override
    public boolean create(Skill skill) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("INSERT INTO Skill (SKill, Description, AccountPcn) VALUES (?,?,?)");
            selectSql.setString(1, skill.getSkill());
            selectSql.setString(2, skill.getDescription());
            selectSql.setLong(3, skill.getAccountPcn());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public boolean update(Skill skill) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("UPDATE Skill SET " +
                    "Skill = ?, Description = ?, AccountPcn = ?" +
                    " WHERE Id = ?");
            selectSql.setString(1, skill.getSkill());
            selectSql.setString(2, skill.getDescription());
            selectSql.setLong(3, skill.getAccountPcn());
            selectSql.setLong(4, skill.getId());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public boolean delete(Skill skill) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("DELETE FROM Skill WHERE Id = ?");
            selectSql.setLong(1, skill.getId());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }
}
