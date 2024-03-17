package com.chatServer;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoom chatRoom;

    public ChatController(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Simple username/password authentication (hardcoded for demonstration)
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody Map<String, String> messageData) {
        String username = messageData.get("username");
        String message = messageData.get("message");

        chatRoom.addMessage(username, message);
        return "Message sent successfully!";
    }

    @GetMapping("/history")
    public List<String> getChatHistory() {
        return chatRoom.getAllMessages();
    }
}
