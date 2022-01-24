package com.mannan.demoapp.Repository;

import com.mannan.demoapp.Model.AccountPackage.Connection;
import com.mannan.demoapp.Repository.AzureConn.DefaultCon;
import com.mannan.demoapp.Repository.Interfaces.IConnectionAzure;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConnectionAzure implements IConnectionAzure {

    public ConnectionAzure() throws SQLException {}
    DefaultCon con = new DefaultCon();

    @Override
    public Connection findByPCNs(Long pcn1, Long pcn2) throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection(con.getCon());
        try {
            Connection connection1 = null;
            PreparedStatement selectSql = connection.prepareStatement(
                    "select * from Connection where (pcn1 = ? AND pcn2 = ?) OR (pcn1 = ? AND pcn2 = ?) ");
            selectSql.setLong(1, pcn1);
            selectSql.setLong(2, pcn2);
            selectSql.setLong(3, pcn2);
            selectSql.setLong(4, pcn1);
            ResultSet result = selectSql.executeQuery();
            while(result.next())
            {
                connection1 = new Connection(result.getLong(1),result.getLong(2), result.getLong(3), result.getInt(4));
            }
            connection.close();
            return connection1;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }

    @Override
    public List<Connection> findPendingByPcn(Long pcn) throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection(con.getCon());
        try {
            List<Connection> connections = new ArrayList<>();
            PreparedStatement selectSql = connection.prepareStatement(
                    "select * from Connection where (Pcn1 = ? OR Pcn2 = ?) AND Accepted IS NULL");
            selectSql.setLong(1, pcn);
            selectSql.setLong(2, pcn);
            ResultSet result = selectSql.executeQuery();
            while(result.next())
            {
                connections.add(new Connection(result.getLong(1),result.getLong(2), result.getLong(3), result.getInt(4)));
            }
            connection.close();
            return connections;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }
    @Override
    public List<Connection> findAcceptedByPcn(Long pcn) throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection(con.getCon());
        try {
            List<Connection> connections = new ArrayList<>();
            PreparedStatement selectSql = connection.prepareStatement(
                    "select * from Connection where (Pcn1 = ? OR Pcn2 = ?) AND Accepted = 1");
            selectSql.setLong(1, pcn);
            selectSql.setLong(2, pcn);
            ResultSet result = selectSql.executeQuery();
            while(result.next())
            {
                connections.add(new Connection(result.getLong(1),result.getLong(2), result.getLong(3), result.getInt(4)));
            }
            connection.close();
            return connections;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }

    @Override
    public boolean createConnection(Connection connect) throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("INSERT INTO Connection (Pcn1, Pcn2) VALUES (?,?)");
            selectSql.setLong(1, connect.getPcn1());
            selectSql.setLong(2, connect.getPcn2());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public boolean deleteConnection(Connection connect) throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("DELETE FROM Connection WHERE Id = ?");
            selectSql.setLong(1, connect.getId());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public boolean update(Connection connect) throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection(con.getCon());
        try {
            connect.setAccepted(1);
            PreparedStatement selectSql = connection.prepareStatement("UPDATE [dbo].[Connection]" +
                    "SET" +
                    "    [Accepted] = ?" +
                    " where Id = ?");
            selectSql.setInt(1, connect.getAccepted());
            selectSql.setLong(2, connect.getId());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }
}
