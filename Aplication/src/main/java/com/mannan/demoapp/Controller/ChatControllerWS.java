package com.mannan.demoapp.Controller;

import com.mannan.demoapp.Interfaces.IChatManager;
import com.mannan.demoapp.Model.ChatPackage.Message;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatControllerWS {

    private ChatController chatController;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{id}")
    public void sendMessage(@DestinationVariable Long id, Message message) {
        simpMessagingTemplate.convertAndSend("/topic/messages/" + id, message);
        chatController.sendMessage(message);
    }
}
