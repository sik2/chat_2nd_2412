package com.ll.chat_pr.global.jpa;

import com.ll.chat_pr.global.jpa.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 기능 사용
public class ChatMessage extends BaseEntity {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "chatRoom_id")
    ChatRoom chatRoom;

    @Column(nullable = false,columnDefinition = "VarChar(255)")
    String writerName;

    @Column(nullable = false,columnDefinition = "TEXT")
    String content;
}
