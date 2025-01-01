package com.ll.chat_pr.domain.home.main.service;

import com.ll.chat_pr.domain.home.main.repository.ChatMessageRepository;
import com.ll.chat_pr.domain.home.main.repository.ChatRoomRepository;
import com.ll.chat_pr.global.jpa.ChatMessage;
import com.ll.chat_pr.global.jpa.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    public void writeMessage(Long roomId, String writerName, String content) {
        ChatRoom target=chatRoomRepository.findById(roomId).orElseThrow();

        ChatMessage chatMessage=ChatMessage.builder()
                .chatRoom(target)
                .writerName(writerName)
                .content(content)
                .build();

        chatMessageRepository.save(chatMessage);
    }
}
