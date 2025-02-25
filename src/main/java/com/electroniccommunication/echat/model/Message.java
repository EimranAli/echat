package com.electroniccommunication.echat.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    String messageId;
    String sender;
    String receiver;
    LocalDateTime timeStamp;
    String textMessage;
    String command;
    String roomId;
}
