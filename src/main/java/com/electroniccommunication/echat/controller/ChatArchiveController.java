package com.electroniccommunication.echat.controller;

import com.electroniccommunication.echat.model.chatarchive.ChatItem;
import com.electroniccommunication.echat.model.chatarchive.GroupChat;
import com.electroniccommunication.echat.service.ChatArchiveService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/echat/chat-archive")
@CrossOrigin
@AllArgsConstructor
public class ChatArchiveController {
    final ChatArchiveService chatArchiveService;

    @GetMapping("/get-group-chat/{roomId}")
    public GroupChat getGroupChat(@PathVariable String roomId) {
        return chatArchiveService.getGroupChat(roomId);
    }

    @PostMapping("/register-group-chat")
    public HttpStatus registerGroupChat(@RequestParam String roomId, @RequestParam String roomCreator) {
        GroupChat groupChat = new GroupChat(roomCreator, roomId);
        return chatArchiveService.registerGroupChat(groupChat);
    }

    @PutMapping("/update-member")
    public HttpStatus updateMember(@RequestParam String roomId, @RequestParam String member) {
        return chatArchiveService.updateMember(roomId, member);
    }

    @PutMapping("/update-chat/{roomId}")
    public HttpStatus updateChat(@PathVariable String roomId, @RequestBody ChatItem chat) {
        return chatArchiveService.updateChat(roomId, chat);
    }
}
