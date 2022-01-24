package com.mannan.demoapp.Manager;

import com.mannan.demoapp.Interfaces.IConnectionManager;
import com.mannan.demoapp.Model.AccountPackage.Connection;
import com.mannan.demoapp.Repository.Interfaces.IConnectionAzure;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionManager implements IConnectionManager {

    IConnectionAzure dataClass;

    public ConnectionManager(IConnectionAzure dataClass) {this.dataClass = dataClass;}

    @Override
    public Connection getConnectionByPCNs(Long pcn1, Long pcn2) {
        try {
            return dataClass.findByPCNs(pcn1, pcn2);
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public List<Connection> getPendingConnections(Long pcn) {
        try {
            return dataClass.findPendingByPcn(pcn);
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public List<Connection> getAcceptedConnections(Long pcn) {
        try {
            return dataClass.findAcceptedByPcn(pcn);
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public boolean addConnection(Connection connection) {
        try {
            Connection checkConnection = getConnectionByPCNs(connection.getPcn1(), connection.getPcn2());
            if (checkConnection == null) {
                return dataClass.createConnection(connection);
            }
            else {return false;}
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean deleteConnection(Connection connection) {
        try {
            return dataClass.deleteConnection(connection);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean updateConnection(Connection connection) {
        try{
            return dataClass.update(connection);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }
}
