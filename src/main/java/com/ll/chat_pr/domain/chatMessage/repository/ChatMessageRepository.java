package com.ll.chat_pr.domain.chatMessage.repository;

import com.ll.chat_pr.domain.chatMessage.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
  List<ChatMessage> findByChatRoom_IdAndIdAfterOrderByIdAsc(Long roomId, Long afterId);
  List<ChatMessage> findByChatRoom_IdOrderByIdAsc(Long roomId);
}
