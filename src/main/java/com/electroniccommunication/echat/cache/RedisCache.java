package com.electroniccommunication.echat.cache;

import com.electroniccommunication.echat.model.chatarchive.ChatItem;
import com.electroniccommunication.echat.model.chatarchive.GroupChat;
import com.electroniccommunication.echat.repository.CustomChatArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// TODO instead of get and save, try to use partial resource update
@Repository
public class RedisCache implements CustomChatArchiveRepository {
    @Autowired
    RedisKeyValueTemplate redisKeyValueTemplate;

    public void registerGroupChat(GroupChat groupChat) {
        redisKeyValueTemplate.insert(groupChat.getRoomId(), groupChat);
    }

    @Override
    public GroupChat getGroupChat(String roomId) {
        return redisKeyValueTemplate.findById(roomId, GroupChat.class).orElse(null);
    }

    @Override
    public void updateMember(String roomId, String member) {
        update(roomId, member);
    }

    @Override
    public void updateChat(String roomId, ChatItem chat) {
        update(roomId, chat);
    }

    private void update(String roomId, Object object) {
        Optional<GroupChat> groupChat = redisKeyValueTemplate.findById(roomId, GroupChat.class);
        groupChat.ifPresentOrElse(gc -> {
            addItem(gc, object);
            redisKeyValueTemplate.update(roomId, gc);
        }, () -> {
            throw new RuntimeException(getExceptionMessage(roomId));
        });
    }

    private void addItem( GroupChat groupChat, Object object) {
        if(object instanceof String member) {
            groupChat.getMembers().add(member);
        } else if(object instanceof ChatItem chatItem)
            groupChat.getChats().add(chatItem);
    }

    private String getExceptionMessage(String roomId) {
        return String.format("group chat for room id : %s not found", roomId);
    }
}
