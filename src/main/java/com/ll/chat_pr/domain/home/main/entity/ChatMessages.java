package com.ll.chat_pr.domain.home.main.entity;

import com.ll.chat_pr.global.jpa.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "chat_messages")
public class ChatMessages extends BaseEntity {

    @Column(nullable = false)
    private String writerName; // 작성자 이름

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom room;

}
