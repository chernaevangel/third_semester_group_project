package com.mannan.demoapp.Interfaces;

import com.mannan.demoapp.Model.ChatPackage.Chat;
import com.mannan.demoapp.Model.ChatPackage.Message;

import java.util.List;

public interface IChatManager {
    Chat getChat(Long pcn1, Long pcn2);
    List<Chat> getChatByPcn(Long pcn);
    boolean sendMessage(Message msg);
    boolean deleteMessage(Long id);
    List<Chat> searchChats(Long pcn, String name);
}
