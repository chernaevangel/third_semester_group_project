package com.mannan.demoapp.Repository.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Connection;

import java.sql.SQLException;
import java.util.List;

public interface IConnectionAzure {
    Connection findByPCNs(Long pcn1, Long pcn2) throws SQLException;
    List<Connection> findPendingByPcn(Long pcn) throws SQLException;
    List<Connection> findAcceptedByPcn(Long pcn) throws SQLException;
    boolean createConnection(Connection connect) throws SQLException;
    boolean deleteConnection(Connection connect) throws SQLException;
    boolean update(Connection connection) throws SQLException;
}
