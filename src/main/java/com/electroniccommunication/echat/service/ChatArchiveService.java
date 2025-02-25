package com.electroniccommunication.echat.service;

import com.electroniccommunication.echat.model.chatarchive.ChatItem;
import com.electroniccommunication.echat.model.chatarchive.GroupChat;
import com.electroniccommunication.echat.repository.ChatArchiveRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatArchiveService {
    final ChatArchiveRepository chatArchiveRepository;

    public GroupChat getGroupChat(String roomId) {
        return chatArchiveRepository.getGroupChat(roomId);
    }

    public HttpStatus registerGroupChat(GroupChat groupChat) {
        chatArchiveRepository.save(groupChat);
        return HttpStatus.ACCEPTED;
    }

    public HttpStatus updateMember(String roomId, String member) {
        chatArchiveRepository.updateMember(roomId, member);
        return HttpStatus.ACCEPTED;
    }

    public HttpStatus updateChat(String roomId, ChatItem chat) {
        chatArchiveRepository.updateChat(roomId, chat);
        return HttpStatus.ACCEPTED;
    }
}
