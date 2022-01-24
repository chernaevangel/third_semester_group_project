package com.mannan.demoapp.Repository;

import com.mannan.demoapp.Model.AccountPackage.Project;
import com.mannan.demoapp.Repository.AzureConn.DefaultCon;
import com.mannan.demoapp.Repository.Interfaces.IProjectAzure;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectAzure implements IProjectAzure {

    public ProjectAzure() throws SQLException {}
    DefaultCon con = new DefaultCon();


    @Override
    public List<Project> findAllByPcn(Long pcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            List<Project> projects = new ArrayList<>();
            PreparedStatement selectSql = connection.prepareStatement(
                    "select * from Project as i where i.[AccountPcn] = ?");
            selectSql.setLong(1, pcn);
            ResultSet result = selectSql.executeQuery();
            while(result.next())
            {
                projects.add(new Project(result.getLong(1),result.getString(2), result.getString(4), result.getString(3), result.getLong(5)));
            }
            return projects;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }

    @Override
    public Project findProject(Project prj) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
       try {
           PreparedStatement selectSql = connection.prepareStatement("SELECT * from Project where accountpcn = ? AND Title like ?");
           selectSql.setLong(1, prj.getAccountPCN());
           selectSql.setString(2, prj.getTitle());

           ResultSet result = selectSql.executeQuery();
           while(result.next())
           {
               return new Project(result.getLong(1), result.getString(2), result.getString(4), result.getString(3), result.getLong(5));
           }

       }
       catch (SQLException e) {e.printStackTrace();}
       connection.close();
       return null;
    }

    @Override
    public boolean create(Project project) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("INSERT INTO Project (Title, Link, Description, AccountPcn) VALUES (?,?,?,?)");
            selectSql.setString(1, project.getTitle());
            selectSql.setString(2, project.getLink());
            selectSql.setString(3, project.getDescription());
            selectSql.setLong(4, project.getAccountPCN());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public boolean update(Project project) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("UPDATE [dbo].[Project]" +
                    "SET" +
                    "    [Title] = ?," +
                    "    [Link] = ?," +
                    "    [Description] = ?," +
                    "    [AccountPcn] = ? " +
                    "WHERE Id = ?");
            selectSql.setString(1, project.getTitle());
            selectSql.setString(2, project.getLink());
            selectSql.setString(3, project.getDescription());
            selectSql.setLong(4, project.getAccountPCN());
            selectSql.setLong(5, project.getId());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public boolean delete(Project project) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("DELETE FROM [dbo].[Project]" +
                    "WHERE Id = ? ");
            selectSql.setLong(1, project.getId());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }
}
