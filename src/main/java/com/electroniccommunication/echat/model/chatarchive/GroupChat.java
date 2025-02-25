package com.electroniccommunication.echat.model.chatarchive;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "group_chat")
public class GroupChat {
    @NotBlank
    String creator;
    @NotBlank
    String roomId;
    List<String> members;
    List<ChatItem> chats;

    public GroupChat(String creator, String roomId) {
        this.creator = creator;
        this.roomId = roomId;
        this.members = new ArrayList<>();
        this.chats = new ArrayList<>();
    }
}
