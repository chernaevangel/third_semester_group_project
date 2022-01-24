package com.mannan.demoapp.Manager;

import com.mannan.demoapp.Interfaces.IExperienceManager;
import com.mannan.demoapp.Model.AccountPackage.Experience;
import com.mannan.demoapp.Repository.Interfaces.IExperienceAzure;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ExperienceManager implements IExperienceManager {

    IExperienceAzure dataClass;

    public ExperienceManager(IExperienceAzure dataClass) {this.dataClass = dataClass;}

    @Override
    public List<Experience> getExperiencesByPcn(Long pcn) {
        try {
            List<Experience> experiences = dataClass.findAllByPcn(pcn);
            Collections.reverse(experiences);
            return experiences;
        }
        catch (Exception e) { e.printStackTrace();}
        return null;
    }

    @Override
    public boolean addExperience(Experience experience) {
        try {
            return dataClass.create(experience);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean updateExperience(Experience experience) {
        try {
            return dataClass.update(experience);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean deleteExperience(Experience experience) {
        try {
            return dataClass.delete(experience);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }
}
