package com.mannan.demoapp.Interfaces;

import com.mannan.demoapp.Model.AccountPackage.Interest;

import java.util.List;

public interface IInterestManager {
    List<Interest> getInterestsByPcn(Long pcn);
    boolean addInterest(Interest interest);
    boolean deleteInterest(Interest interest);
}
