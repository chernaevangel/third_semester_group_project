package com.mannan.demoapp.Model.ChatPackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Message {
    private Long id;
    private Long chatId;
    private Long senderPCN;
    private String message;
    private String senderName;
    //private var attachedFile;

    public Message(Long senderPCN, String message, Long chatId) {
        this.senderPCN = senderPCN;
        this.message = message;
        this.chatId = chatId;
    }
}

