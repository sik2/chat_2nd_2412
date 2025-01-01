package com.ll.chat_pr.domain.chatMessage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.chat_pr.domain.chatRoom.entity.ChatRoom;
import com.ll.chat_pr.global.jpa.BaseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class ChatMessage extends BaseEntity {
  @JsonIgnore
  @ManyToOne
  private ChatRoom chatRoom;
  private String writerName;
  private String content;

  public ChatMessage(ChatRoom chatRoom, String writerName, String content) {
    this.chatRoom = chatRoom;
    this.writerName = writerName;
    this.content = content;
  }
}
