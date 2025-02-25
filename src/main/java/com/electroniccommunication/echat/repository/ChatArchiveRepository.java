package com.electroniccommunication.echat.repository;

import com.electroniccommunication.echat.model.chatarchive.GroupChat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatArchiveRepository extends MongoRepository<GroupChat, Long>, CustomChatArchiveRepository {
}
