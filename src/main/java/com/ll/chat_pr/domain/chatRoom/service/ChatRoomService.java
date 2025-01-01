package com.ll.chat_pr.domain.chatRoom.service;

import com.ll.chat_pr.domain.chatRoom.entity.ChatRoom;
import com.ll.chat_pr.domain.chatRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {
  private final ChatRoomRepository chatRoomRepository;

  public List<ChatRoom> findAll() {
    return chatRoomRepository.findAll();
  }


  public ChatRoom makeChatRoom(String name) {
    return chatRoomRepository.save(new ChatRoom(name));
  }

  public ChatRoom findById(Long id) {
    return chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
  }
}
