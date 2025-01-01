package com.ll.chat_pr.domain.home.main.repository;

import com.ll.chat_pr.global.jpa.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
