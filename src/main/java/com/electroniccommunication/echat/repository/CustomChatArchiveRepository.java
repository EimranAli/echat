package com.electroniccommunication.echat.repository;

import com.electroniccommunication.echat.model.chatarchive.ChatItem;
import com.electroniccommunication.echat.model.chatarchive.GroupChat;

import java.util.Set;

public interface CustomChatArchiveRepository {
    GroupChat getGroupChat(String roomId);

    void updateMember(String roomId, String member);
    void updateChat(String roomId, ChatItem chat);
}
