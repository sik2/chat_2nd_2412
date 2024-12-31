package com.ll.chat_pr.domain.chat.chat.chatMessage.entity;

import com.ll.chat_pr.domain.chat.chat.chatRoom.entity.ChatRoom;
import com.ll.chat_pr.global.jpa.BaseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Setter
@Getter
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@ToString(callSuper = true)
public class ChatMessage extends BaseEntity {
    @ManyToOne
    private ChatRoom chatRoom;

    private String writerName;

    private String content;
}
