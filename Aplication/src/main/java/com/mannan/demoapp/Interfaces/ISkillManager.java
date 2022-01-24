package com.mannan.demoapp.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Skill;

import java.util.List;

public interface ISkillManager {
    List<Skill> getSkillsByPcn(Long pcn);
    boolean addSkill(Skill skill);
    boolean deleteSkill(Skill skill);
    boolean updateSkill(Skill skill);
}