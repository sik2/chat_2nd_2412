package com.ll.chat_pr.domain.chatRoom.repository;

import com.ll.chat_pr.domain.chatRoom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
