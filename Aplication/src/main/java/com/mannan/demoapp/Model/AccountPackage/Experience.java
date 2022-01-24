package com.mannan.demoapp.Model.AccountPackage;

import lombok.*;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Experience {
    Long id;
    String type;
    String duration;
    String description;
    String institute;
    Long accountPcn;

    public Experience(String type, String duration, String description, String institute, Long accountPcn){
        this.type = type;
        this.description = description;
        this.duration = duration;
        this.institute = institute;
        this.accountPcn = accountPcn;
    }

}
