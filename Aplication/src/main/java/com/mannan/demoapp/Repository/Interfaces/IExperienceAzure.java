package com.mannan.demoapp.Repository.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Experience;

import java.sql.SQLException;
import java.util.List;

public interface IExperienceAzure {
    List<Experience> findAllByPcn(Long pcn) throws SQLException;
    boolean create(Experience experience) throws SQLException;
    boolean update(Experience experience) throws SQLException;
    boolean delete(Experience experience) throws SQLException;
}
