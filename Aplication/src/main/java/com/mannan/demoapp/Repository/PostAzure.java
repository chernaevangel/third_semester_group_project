package com.mannan.demoapp.Repository;

import com.mannan.demoapp.Model.PostPackage.Post;
import com.mannan.demoapp.Repository.AzureConn.DefaultCon;
import com.mannan.demoapp.Repository.Interfaces.IPostAzure;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostAzure implements IPostAzure {

    public PostAzure() throws SQLException {}

    DefaultCon con = new DefaultCon();


    @Override
    public boolean create(Post post) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            LocalDate anotherSummerDay = LocalDate.now();
            String date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(anotherSummerDay).toString();
            PreparedStatement selectSql = connection.prepareStatement("INSERT  INTO Post " +
                    "(Title, Description, AccountPCN, Date) " +
                    "VALUES (?,?,?, ?)");
            selectSql.setString(1, post.getTitle());
            selectSql.setString(2, post.getDescription());
            selectSql.setLong(3, post.getAccountPCN());
            selectSql.setString(4, date);

            selectSql.executeUpdate();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public Post getPostById(Long id) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("Select p.id, a.Name, a.[Image],  p.Title, CAST(p.[Description] as varchar(max)), p.[Date], p.AccountPCN from Post as p inner join account as a on a.PCN = p.AccountPCN" +
                    " where p.id = ?");
            selectSql.setLong(1, id);
            ResultSet result = selectSql.executeQuery();
            while(result.next()) {
                return new Post(result.getLong(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getLong(7), result.getString(6) );
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }

    @Override
    public List<Post> getAccountPostsByPcn(Long pcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement selectSql = connection.prepareStatement("Select p.id, a.Name, a.[Image],  p.Title, CAST(p.[Description] as varchar(max)), p.[Date], p.AccountPCN from Post as p inner join account as a on a.PCN = p.AccountPCN" +
                    " Where AccountPcn = ?");
            selectSql.setLong(1, pcn);
            ResultSet result = selectSql.executeQuery();

            while(result.next()) {
                posts.add(new Post(result.getLong(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getLong(7), result.getString(6)));
            }
            return posts;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("DELETE FROM POST " +
                    "WHERE Id = ?");
            selectSql.setLong(1, id);
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public List<Post> getAccountNewsfeed(Long pcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            List<Post> posts = new ArrayList<>();
           PreparedStatement selectSql = connection.prepareStatement("select distinct p.id, a.Name, a.[Image],  p.Title, CAST(p.[Description] as varchar(max)), p.[Date], p.AccountPCN from post as p inner join account as a on a.PCN = p.AccountPCN inner join Connection as c on p.AccountPCN = c.Pcn1 or p.AccountPCN = c.Pcn2  where c.pcn1 = ? or c.pcn2 = ? ORDER by id DESC");
           selectSql.setLong(1, pcn);
           selectSql.setLong(2, pcn);
           ResultSet result = selectSql.executeQuery();
           while (result.next()) {
               posts.add(new Post(result.getLong(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getLong(7), result.getString(6)));
           }
           return posts;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
        }
    }

