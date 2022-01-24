package com.mannan.demoapp.Model.AccountPackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Interest {
    private Long id;
    private String interest;
    private Long accountPCN;

    public Interest(String interest, Long accountPCN)
    {
        this.interest = interest;
        this.accountPCN = accountPCN;
    }
}