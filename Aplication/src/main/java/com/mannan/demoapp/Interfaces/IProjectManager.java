package com.mannan.demoapp.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Project;

import java.util.List;

public interface IProjectManager {
    List<Project> getProjectsByPcn(Long pcn);
    Project getProject(Project project);
    boolean deleteProject(Project project);
    boolean addProject(Project project);
    boolean updateProject(Project project);
}
