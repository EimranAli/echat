package com.electroniccommunication.echat.model.chatarchive;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "group_chat")
public class GroupChat {
    @Id
    @NotBlank
    String roomId;
    @NotBlank
    String creator;
    Set<String> members;
    Set<ChatItem> chats;

    public GroupChat(String creator, String roomId) {
        this.creator = creator;
        this.roomId = roomId;
        this.members = new HashSet<>();
        this.chats = new HashSet<>();
    }
}
