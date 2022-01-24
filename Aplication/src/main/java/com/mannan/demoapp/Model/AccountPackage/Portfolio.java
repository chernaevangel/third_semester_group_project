package com.mannan.demoapp.Model.AccountPackage;

import com.mannan.demoapp.Model.AccountPackage.Experience;
import com.mannan.demoapp.Model.AccountPackage.Interest;
import com.mannan.demoapp.Model.AccountPackage.Project;
import com.mannan.demoapp.Model.AccountPackage.Skill;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Portfolio {
    private List<Interest> interests;
    private List<Skill> skills;
    private List<Experience> experiences;
    private List<Project> projects;

    public void addProject(Project project){
        this.projects.add(project);
    }
}
