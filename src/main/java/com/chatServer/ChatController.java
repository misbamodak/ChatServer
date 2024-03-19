package com.chatServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
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

        chatMessageRepository.save(new ChatMessage(username, message));
        return "Message sent successfully!";
    }

    @GetMapping("/history")
    public List<ChatMessage> getChatHistory() {
        return chatMessageRepository.findAll();
    }
}
