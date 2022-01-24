package com.mannan.demoapp.Repository;

import com.mannan.demoapp.Model.ChatPackage.Chat;
import com.mannan.demoapp.Model.ChatPackage.Message;
import com.mannan.demoapp.Repository.AzureConn.DefaultCon;
import com.mannan.demoapp.Repository.Interfaces.IChatAzure;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ChatAzure implements IChatAzure {

    DefaultCon con = new DefaultCon();

    public ChatAzure() throws SQLException {
    }

    @Override
    public Chat getChatByPCNs(Long pcn1, Long pcn2) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            Chat chat = null;
            PreparedStatement selectSql = connection.prepareStatement("Select c.Id, c.Pcn1, c.Pcn2, a1.Name, a2.Name from Chat as c " +
                    "inner join  account as a1 on c.Pcn1 = a1.Pcn " +
                    "inner join account as a2 on c.Pcn2 = a2.Pcn " +
                    "where (PCN1 = ? AND PCN2 = ?) OR (PCN1 = ? AND PCN2 = ?)");
            selectSql.setLong(1, pcn1);
            selectSql.setLong(2, pcn2);
            selectSql.setLong(3, pcn2);
            selectSql.setLong(4, pcn1);
            ResultSet result = selectSql.executeQuery();
            while (result.next()) {
                chat = new Chat(result.getLong(1), result.getLong(2), result.getLong(3), result.getString(4), result.getString(5), new ArrayList<>());
                chat.loadMessages(getChatMessages(chat.getId()));
            }
            connection.close();
            return chat;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }

    @Override
    public List<Chat> getChatByPCN(Long pcn) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            List<Chat> chats = new ArrayList<>();
            PreparedStatement selectSql = connection.prepareStatement("Select c.Id, c.Pcn1, c.Pcn2, a1.Name, a2.Name from Chat as c " +
                    "inner join  account as a1 on c.Pcn1 = a1.Pcn " +
                    "inner join account as a2 on c.Pcn2 = a2.Pcn " +
                    "where PCN1 = ? OR PCN2 = ? " +
                    "order by c.LastMessage desc");
            selectSql.setLong(1, pcn);
            selectSql.setLong(2, pcn);
            ResultSet result = selectSql.executeQuery();
            while (result.next()) {
                Chat chat = new Chat(result.getLong(1), result.getLong(2), result.getLong(3), result.getString(4), result.getString(5), new ArrayList<>());
                chat.loadMessages(getChatMessages(chat.getId()));
                chats.add(chat);
            }
            connection.close();
            return chats;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }

    @Override
    public boolean create(Chat chat) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("INSERT INTO Chat " +
                    "(PCN1, PCN2) " +
                    "VALUES (? , ?)");
            selectSql.setLong(1, chat.getPcn1());
            selectSql.setLong(2, chat.getPcn2());
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    @Override
    public boolean sendMessage(Message msg) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
            try {
                if (getChatById(msg.getChatId())) {
                    PreparedStatement selectSql = connection.prepareStatement("Insert into Message " +
                            "(ChatId, SenderPCN, Message) " +
                            "VALUES (?, ?, ?)");
                    selectSql.setLong(1, msg.getChatId());
                    selectSql.setLong(2, msg.getSenderPCN());
                    selectSql.setString(3, msg.getMessage());
                    selectSql.executeUpdate();
                    PreparedStatement selectSql2 = connection.prepareStatement("Update Chat " +
                            "SET LastMessage = GETDATE()" +
                            "Where Id = ?");
                    selectSql2.setLong(1, msg.getChatId());
                    selectSql2.executeUpdate();
                    connection.close();
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.close();
        return false;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            PreparedStatement selectSql = connection.prepareStatement("Delete from Message " +
                    "where id = ?");
            selectSql.setLong(1, id);
            selectSql.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return false;
    }

    private List<Message> getChatMessages(Long id) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        try {
            List<Message> messages = new ArrayList<>();
            PreparedStatement selectSql = connection.prepareStatement("select m.Id, m.ChatId, m.SenderPCN, m.message, a.Name from message as m " +
                    "inner join account as a on a.Pcn = m.senderpcn " +
                    "where chatId = ? " +
                    "order by Id");
            selectSql.setLong(1, id);
            ResultSet result = selectSql.executeQuery();
            while (result.next()) {
                messages.add(new Message(result.getLong(1), result.getLong(2), result.getLong(3), result.getString(4), result.getString(5)));
            }
            Collections.reverse(messages);
            connection.close();
            return messages;
        }
        catch (SQLException e) {e.printStackTrace();}
        connection.close();
        return null;
    }
    private boolean getChatById(Long id) throws SQLException {
        Connection connection = DriverManager.getConnection(con.getCon());
        int rows = 0;
        try {
            PreparedStatement selectSql = connection.prepareStatement("Select * From Chat " +
                    "Where id = ?");
            selectSql.setLong(1, id);
            ResultSet result = selectSql.executeQuery();
            while (result.next()) {rows++;}
            connection.close();
        }
        catch (SQLException e) {e.printStackTrace();}
        if (rows > 0) {return true;}
        connection.close();
        return false;
    }

}
