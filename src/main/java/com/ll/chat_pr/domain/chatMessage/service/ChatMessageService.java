package com.ll.chat_pr.domain.chatMessage.service;

import com.ll.chat_pr.domain.chatMessage.entity.ChatMessage;
import com.ll.chat_pr.domain.chatMessage.repository.ChatMessageRepository;
import com.ll.chat_pr.domain.chatRoom.entity.ChatRoom;
import com.ll.chat_pr.domain.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatMessageService {
  private final ChatMessageRepository chatMessageRepository;
  private final ChatRoomService chatRoomService;

  @Transactional
  public ChatMessage write(Long roomId, String writerName, String content) {
    ChatRoom chatRoom = chatRoomService.findById(roomId);

    ChatMessage chatMessage = ChatMessage
        .builder()
        .chatRoom(chatRoom)
        .writerName(writerName)
        .content(content)
        .build();

    return chatMessageRepository.save(chatMessage);
  }

  public List<ChatMessage> findByRoomIdAndIdAfter(Long roomId, Long afterId) {
    return chatMessageRepository.findByChatRoom_IdAndIdAfterOrderByIdAsc(roomId, afterId);
  }

  public List<ChatMessage> findByChatRoomId(Long roomId) {
    return chatMessageRepository.findByChatRoom_IdOrderByIdAsc(roomId);
  }
}
