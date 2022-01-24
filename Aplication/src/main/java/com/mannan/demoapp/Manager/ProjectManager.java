package com.mannan.demoapp.Manager;

import com.mannan.demoapp.Interfaces.IProjectManager;
import com.mannan.demoapp.Model.AccountPackage.Project;
import com.mannan.demoapp.Repository.Interfaces.IProjectAzure;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectManager implements IProjectManager {

    IProjectAzure dataClass;

    public ProjectManager(IProjectAzure dataClass) {this.dataClass = dataClass;}

    @Override
    public List<Project> getProjectsByPcn(Long pcn) {
        try {
            List<Project> projects = dataClass.findAllByPcn(pcn);
            Collections.reverse(projects);
            return projects;
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public Project getProject(Project project) {
        try {
            return dataClass.findProject(project);
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public boolean deleteProject(Project project) {
        try {
            return dataClass.delete(project);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean addProject(Project project) {
        try {
            return dataClass.create(project);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean updateProject(Project project) {
        try {
            return dataClass.update(project);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }
}
