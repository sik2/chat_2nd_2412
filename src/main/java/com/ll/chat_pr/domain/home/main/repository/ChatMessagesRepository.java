package com.ll.chat_pr.domain.home.main.repository;

import com.ll.chat_pr.domain.home.main.chatMessageDto.ChatMessageResponse;
import com.ll.chat_pr.domain.home.main.entity.ChatMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessagesRepository extends JpaRepository<ChatMessages, Long> {

    List<ChatMessages> findByRoomIdAndIdGreaterThan (Long roomId, Long afterId);
}

