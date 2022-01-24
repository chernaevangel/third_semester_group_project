package com.mannan.demoapp.Repository.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Skill;

import java.sql.SQLException;
import java.util.List;

public interface ISkillAzure {
    List<Skill> findAllByPcn(Long pcn) throws SQLException;
    boolean create(Skill skill) throws SQLException;
    boolean update(Skill skill) throws SQLException;
    boolean delete(Skill skill) throws SQLException;
}
