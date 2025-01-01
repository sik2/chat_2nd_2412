package com.ll.chat_pr.domain.chatRoom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.chat_pr.domain.chatMessage.entity.ChatMessage;
import com.ll.chat_pr.global.jpa.BaseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity // JPA 엔티티임을 명시
@SuperBuilder // BaseEntity의 빌더 패턴을 사용하기 위함
@NoArgsConstructor // JPA는 기본 생성자가 필요함
public class ChatRoom extends BaseEntity {
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "chatRoom")
    @OrderBy("id asc")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public ChatRoom(String name) {
        this.name = name;
    }
}
