package com.mannan.demoapp.Manager;

import com.mannan.demoapp.Interfaces.IChatManager;
import com.mannan.demoapp.Model.ChatPackage.Chat;
import com.mannan.demoapp.Model.ChatPackage.Message;
import com.mannan.demoapp.Repository.Interfaces.IChatAzure;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class ChatManager implements IChatManager {

    private IChatAzure dataClass;

    public ChatManager(IChatAzure dataClass) {this.dataClass = dataClass;}

    public boolean createChat(Long pcn1, Long pcn2) {
        try {
            return dataClass.create(new Chat(pcn1, pcn2));
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public Chat getChat(Long pcn1, Long pcn2) {
        try {
            Chat chat = dataClass.getChatByPCNs(pcn1, pcn2);
            if (chat == null) {
                Chat newChat = new Chat(pcn1, pcn2);
                if (dataClass.create(newChat))
                {
                    Collections.reverse(newChat.getMessages());
                    return newChat;
                }
            }
            Collections.reverse(chat.getMessages());
            return chat;
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    public List<Chat> getChatByPcn(Long pcn) {
        try {
            return dataClass.getChatByPCN(pcn);
        }
        catch (Exception e) { e.printStackTrace();}
        return null;
    }

    @Override
    public boolean sendMessage(Message msg) {
        try {
            return dataClass.sendMessage(msg);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public boolean deleteMessage(Long id) {
        try {
            return dataClass.delete(id);
        }
        catch (Exception e) {e.printStackTrace();}
        return false;
    }

    @Override
    public List<Chat> searchChats(Long pcn, String name) {
        try {
            List<Chat> chats = getChatByPcn(pcn);
            chats.removeIf(chat -> !chat.getAccount1().toLowerCase().startsWith(name.toLowerCase()) && !chat.getAccount2().toLowerCase().startsWith(name.toLowerCase()));
            return chats;
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

}
