package com.ll.chat_pr.domain.home.main.entity;

import com.ll.chat_pr.global.jpa.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "chat_room")
public class ChatRoom extends BaseEntity {

    @Column(nullable = false)
    private String roomName; // 채팅방 이름

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ChatMessages> messages = new ArrayList<>();


}