package com.electroniccommunication.echat.repository;

import com.electroniccommunication.echat.model.chatarchive.ChatItem;
import com.electroniccommunication.echat.model.chatarchive.GroupChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomChatArchiveRepositoryImpl implements CustomChatArchiveRepository {
    @Autowired
    MongoTemplate mongoTemplate;


    public GroupChat getGroupChat(String roomId) {
        Query query = new Query(Criteria.where("roomId").is(roomId));
        return mongoTemplate.find(query, GroupChat.class).get(0);
    }

    public void updateMember(String roomId, String member) {
        Query query = new Query(Criteria.where("roomId").is(roomId));
        Update update = new Update();
        update.addToSet("members").value(member);
        mongoTemplate.updateFirst(query, update, GroupChat.class);
    }

    public void updateChat(String roomId, ChatItem chat) {
        Query query = new Query(Criteria.where("roomId").is(roomId));
        Update update = new Update();
        update.addToSet("chats").value(chat);
        mongoTemplate.updateFirst(query, update, GroupChat.class);
    }
}
