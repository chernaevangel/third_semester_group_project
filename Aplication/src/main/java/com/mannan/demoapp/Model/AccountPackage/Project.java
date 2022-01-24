package com.mannan.demoapp.Model.AccountPackage;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Project {
    private Long id;
    private String title;
    private String description;
    private String link;
    private Long accountPCN;
    //private var uploadedProject;

    public Project(String title, String description, String link, Long accountPCN) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.accountPCN = accountPCN;
    }
}
