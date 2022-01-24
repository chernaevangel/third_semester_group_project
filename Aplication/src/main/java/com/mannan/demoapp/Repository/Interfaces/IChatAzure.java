package com.mannan.demoapp.Repository.Interfaces;

import com.mannan.demoapp.Model.ChatPackage.Chat;
import com.mannan.demoapp.Model.ChatPackage.Message;

import java.sql.SQLException;
import java.util.List;

public interface IChatAzure {
    Chat getChatByPCNs(Long pcn1, Long pcn2) throws SQLException;
    List<Chat> getChatByPCN(Long pcn) throws SQLException;
    boolean create(Chat chat) throws SQLException;
    boolean sendMessage(Message msg) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
