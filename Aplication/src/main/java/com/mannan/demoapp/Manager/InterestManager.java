package com.mannan.demoapp.Manager;

import com.mannan.demoapp.Interfaces.IInterestManager;
import com.mannan.demoapp.Model.AccountPackage.Interest;
import com.mannan.demoapp.Repository.Interfaces.IInterestAzure;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class InterestManager implements IInterestManager {

    private IInterestAzure dataClass;

    public InterestManager(IInterestAzure dataClass) {this.dataClass = dataClass;}

    @Override
    public List<Interest> getInterestsByPcn(Long pcn) {
        try {
            List<Interest> interests = dataClass.findAllByPcn(pcn);
            Collections.reverse(interests);
            return interests;
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public boolean addInterest(Interest interest) {
        try {
            return dataClass.create(interest);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean deleteInterest(Interest interest) {
        try {
            return dataClass.delete(interest);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }
}
