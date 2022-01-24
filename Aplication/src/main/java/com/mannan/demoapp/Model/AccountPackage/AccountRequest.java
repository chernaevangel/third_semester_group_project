package com.mannan.demoapp.Model.AccountPackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AccountRequest {
    private Long pcn1;
    private Long pcn2;
    private int visibility;
    private int accepted;
}
