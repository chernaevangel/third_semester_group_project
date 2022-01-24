package com.mannan.demoapp.Model.AccountPackage;

import lombok.*;

import java.sql.Blob;

@Getter @NoArgsConstructor @AllArgsConstructor @Setter
public class Account {
    private Long pcn;
    private String name;
    private String bio;
    private String type;
    private int visibility;
   // private int language;
    private String binaryImage;

}
