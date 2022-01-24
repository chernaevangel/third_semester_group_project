package com.mannan.demoapp;

import com.mannan.demoapp.Manager.ChatManager;
import com.mannan.demoapp.Model.ChatPackage.Chat;
import com.mannan.demoapp.Model.ChatPackage.Message;
import com.mannan.demoapp.Repository.Interfaces.IChatAzure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class ChatManagerTest {

    @Mock
    IChatAzure dataClass;
    ChatManager chatManager;

    @BeforeEach
    void beforeEach() {
        chatManager = new ChatManager(dataClass);
    }

    @Test
    void getChat() throws SQLException {

        chatManager.getChat(1234L, 2345L);

        verify(dataClass).getChatByPCNs(1234L, 2345L);
    }

    @Test
    void getChatByPcn() throws SQLException {
        chatManager.getChatByPcn(1234L);

        verify(dataClass).getChatByPCN(1234L);
    }

    @Test
    void sendMessage() throws SQLException {
        Message msg = new Message(1234L, "Test msg", 1L);

        chatManager.sendMessage(msg);

        verify(dataClass).sendMessage(msg);
    }

}
