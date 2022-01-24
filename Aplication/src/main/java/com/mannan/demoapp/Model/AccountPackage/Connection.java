package com.mannan.demoapp.Model.AccountPackage;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter @Setter @Data @AllArgsConstructor @NoArgsConstructor
public class Connection {
    private Long id;
    private Long pcn1;
    private Long pcn2;
    private int accepted;

    public Connection(Long pcn1, Long pcn2, int accepted) {
        this.pcn1 = pcn1;
        this.pcn2 = pcn2;
        this.accepted = accepted;
    }
}
