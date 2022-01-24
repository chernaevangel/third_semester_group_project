package com.mannan.demoapp.Model.ChatPackage;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Chat {
    private Long id;
    private Long pcn1;
    private Long pcn2;
    private String account1;
    private String account2;
    private List<Message> messages;

    public Chat(Long pcn1, Long pcn2) {
        this.pcn1 = pcn1;
        this.pcn2 = pcn2;
        messages = new ArrayList<>();
    }

    public void addMessage(Message msg) {messages.add(msg);}
    public void loadMessages(List<Message> msgs) {messages.addAll(msgs);}
}
