package com.mannan.demoapp.Repository.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Project;

import java.sql.SQLException;
import java.util.List;

public interface IProjectAzure {
    List<Project> findAllByPcn(Long pcn) throws SQLException;
    Project findProject(Project project) throws SQLException;
    boolean create(Project project) throws SQLException;
    boolean update(Project project) throws SQLException;
    boolean delete(Project project) throws SQLException;
}
