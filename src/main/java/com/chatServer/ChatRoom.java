package com.chatServer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChatRoom {

    private final Map<String, List<String>> chatRoom;

    public ChatRoom() {
        this.chatRoom = new HashMap<>();
    }

    public void addMessage(String username, String message) {
        chatRoom.computeIfAbsent(username, k -> new ArrayList<>()).add(message);
    }

    public List<String> getAllMessages() {
        List<String> allMessages = new ArrayList<>();
        chatRoom.values().forEach(allMessages::addAll);
        return allMessages;
    }
}
