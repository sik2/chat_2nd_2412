package com.ll.chat_pr.domain.home.main.service;

import com.ll.chat_pr.domain.home.main.repository.ChatRoomRepository;
import com.ll.chat_pr.global.jpa.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public Long createChatRoom(String roomName) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(roomName)
                .build();

        chatRoomRepository.save(chatRoom);
        return chatRoom.getId();
    }

    public ChatRoom findChatRoom(Long id) {
        return chatRoomRepository.findById(id).orElseThrow();
    }


    public List<ChatRoom> findAllChatRooms() {
        return chatRoomRepository.findAll();
    }
}
