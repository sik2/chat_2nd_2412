package com.ll.chat_pr.domain.home.main.service;

import com.ll.chat_pr.domain.home.main.chatMessageDto.ChatMessageResponse;
import com.ll.chat_pr.domain.home.main.dto.ChatRoomListDto;
import com.ll.chat_pr.domain.home.main.dto.ChatRoomMakeRequest;
import com.ll.chat_pr.domain.home.main.entity.ChatMessages;
import com.ll.chat_pr.domain.home.main.entity.ChatRoom;
import com.ll.chat_pr.domain.home.main.repository.ChatMessagesRepository;
import com.ll.chat_pr.domain.home.main.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessagesRepository chatMessagesRepository;
    private final ChatRoomRepository chatRoomRepository;

    //채팅방 목록 조회
    public List<ChatRoomListDto> getChatRoomList(){
        return chatRoomRepository.findAll().stream()
                .map(ChatRoomListDto::of)
                .collect(Collectors.toList());
    }
    // 채팅방 개설
    public ChatRoom createChatRoom (ChatRoomMakeRequest chatRoomReq) {
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(chatRoomReq.getRoomName())
                .build();
        return chatRoomRepository.save(chatRoom);
    }


    public ChatRoom enterChatRoom (Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }

    // 메시지 저장
    public void saveMessage(Long roomId, String writerName, String content) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        ChatMessages chatMessage = ChatMessages.builder()
                .room(chatRoom)
                .writerName(writerName)
                .content(content)
                .createDate(LocalDateTime.now())
                .build();
        chatMessagesRepository.save(chatMessage);
    }

    // afterId 이후의 메시지 조회
    public List<ChatMessageResponse> getMessagesAfter(Long roomId, Long afterId) {
        return chatMessagesRepository.findByRoomIdAndIdGreaterThan(roomId, afterId)
                .stream()
                .map(message -> ChatMessageResponse.builder()
                        .roomId(roomId)
                        .writerName(message.getWriterName())
                        .content(message.getContent())
                        .sendTime(message.getCreateDate())
                        .build())
                .toList();
    }
}
