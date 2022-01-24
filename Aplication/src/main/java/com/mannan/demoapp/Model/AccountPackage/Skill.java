package com.mannan.demoapp.Model.AccountPackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class Skill {
    private Long id;
    private String skill;
    private String description;
    private Long accountPcn;

    public Skill(String skill, String description, Long accountPcn) {
        this.skill = skill;
        this.description = description;
        this.accountPcn = accountPcn;
    }
}
