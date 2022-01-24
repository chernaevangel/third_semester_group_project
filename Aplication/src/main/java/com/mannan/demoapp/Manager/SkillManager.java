package com.mannan.demoapp.Manager;

import com.mannan.demoapp.Interfaces.ISkillManager;
import com.mannan.demoapp.Model.AccountPackage.Skill;
import com.mannan.demoapp.Repository.Interfaces.ISkillAzure;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SkillManager implements ISkillManager {

    ISkillAzure dataClass;
    public SkillManager(ISkillAzure dataClass) {this.dataClass = dataClass;}

    @Override
    public List<Skill> getSkillsByPcn(Long pcn) {
        try {
            List<Skill> skills = dataClass.findAllByPcn(pcn);
            Collections.reverse(skills);
            return skills;
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public boolean addSkill(Skill skill) {
        try {
            return dataClass.create(skill);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean deleteSkill(Skill skill) {
        try {
            return dataClass.delete(skill);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean updateSkill(Skill skill) {
        try {
            return dataClass.update(skill);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }
}
