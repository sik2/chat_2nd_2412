package com.ll.chat_pr.global.jpa;

import com.ll.chat_pr.global.jpa.BaseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 기능 사용
public class ChatRoom extends BaseEntity {

    @Column(nullable = false,columnDefinition = "VarChar(255)")
    String name;

    @OneToMany(mappedBy = "chatRoom")
    List<ChatMessage> chatMessages;
}
