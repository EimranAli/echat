package com.electroniccommunication.echat.controller;

import com.electroniccommunication.echat.model.Message;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/private-rooms")
    public void sendPublicMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSend("/chatrooms/" + message.getRoomId(), message);
    }
}
