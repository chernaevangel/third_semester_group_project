package com.mannan.demoapp.Repository.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Interest;

import java.sql.SQLException;
import java.util.List;

public interface IInterestAzure {
    List<Interest> findAllByPcn(Long pcn) throws SQLException;
    boolean delete(Interest interest) throws SQLException;
    boolean create(Interest interest) throws SQLException;
}
