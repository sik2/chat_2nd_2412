package com.ll.chat_pr.domain.chat.chat.chatMessage.repository;

import com.ll.chat_pr.domain.chat.chat.chatMessage.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomIdAndIdAfter(long roomId, long afterId);
}
