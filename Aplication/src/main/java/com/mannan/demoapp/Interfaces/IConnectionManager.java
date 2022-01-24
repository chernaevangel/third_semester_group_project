package com.mannan.demoapp.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Connection;

import java.util.List;

public interface IConnectionManager {
    Connection getConnectionByPCNs(Long pcn1, Long pcn2);
    List<Connection> getPendingConnections(Long pcn);
    List<Connection> getAcceptedConnections(Long pcn);
    boolean addConnection(Connection connection);
    boolean deleteConnection(Connection connection);
    boolean updateConnection(Connection connection);
}
