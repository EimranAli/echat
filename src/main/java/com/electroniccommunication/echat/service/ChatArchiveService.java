package com.electroniccommunication.echat.service;

import com.electroniccommunication.echat.cache.RedisCache;
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
    final RedisCache redisCache;

    public GroupChat getGroupChat(String roomId) {
        return redisCache.getGroupChat(roomId);
    }

    public HttpStatus registerGroupChat(GroupChat groupChat) {
        redisCache.registerGroupChat(groupChat);
        new Thread(() -> writeBackToDataBase(groupChat)).start();
        return HttpStatus.ACCEPTED;
    }

    public HttpStatus updateMember(String roomId, String member) {
        redisCache.updateMember(roomId, member);
        new Thread(() -> writeBackToDataBase(roomId, member)).start();
        return HttpStatus.ACCEPTED;
    }

    public HttpStatus updateChat(String roomId, ChatItem chat) {
        redisCache.updateChat(roomId, chat);
        new Thread(() -> writeBackToDataBase(roomId, chat)).start();
        return HttpStatus.ACCEPTED;
    }

    private void writeBackToDataBase(Object... args) {
        if(args[0] instanceof GroupChat groupChat)
            chatArchiveRepository.save(groupChat);
        else if( args[1] instanceof String member)
            chatArchiveRepository.updateMember(args[0].toString(), member);
        else if(args[1] instanceof ChatItem chat)
            chatArchiveRepository.updateChat(args[0].toString(), chat);
    }
}
