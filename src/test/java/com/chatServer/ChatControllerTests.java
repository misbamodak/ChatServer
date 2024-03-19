package com.chatServer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ChatControllerTests {

    @MockBean
    private ChatMessageRepository chatMessageRepository;

    private ChatController chatController;

    @BeforeEach
    public void setUp() {
        chatController = new ChatController(chatMessageRepository);
    }

    @Test
    public void testLogin_Successful() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "admin123");

        assertEquals("Login successful!", chatController.login(credentials));
    }

    @Test
    public void testLogin_InvalidCredentials() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "user");
        credentials.put("password", "wrongpassword");

        assertEquals("Invalid credentials!", chatController.login(credentials));
    }

    @Test
    public void testSendMessage() {
        Map<String, String> messageData = new HashMap<>();
        messageData.put("username", "user1");
        messageData.put("message", "Hello!");

        String username = messageData.get("username");
        String message = messageData.get("message");
        ChatMessage chatMessage = new ChatMessage(username, message);

        when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(chatMessage);

        assertEquals("Message sent successfully!", chatController.sendMessage(messageData));
        verify(chatMessageRepository, times(1)).save(any(ChatMessage.class));
    }

    @Test
    public void testGetChatHistory() {
        List<ChatMessage> messages = Arrays.asList(
                new ChatMessage("user1", "Message 1"),
                new ChatMessage("user2", "Message 2")
        );

        when(chatMessageRepository.findAll()).thenReturn(messages);

        assertEquals(messages, chatController.getChatHistory());
        verify(chatMessageRepository, times(1)).findAll();
    }
}