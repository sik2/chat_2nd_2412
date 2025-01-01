package com.ll.chat_pr.domain.home.main.controller;

import com.ll.chat_pr.domain.home.main.chatMessageDto.ChatMessageRequest;
import com.ll.chat_pr.domain.home.main.chatMessageDto.ChatMessageResponse;
import com.ll.chat_pr.domain.home.main.entity.ChatRoom;
import com.ll.chat_pr.domain.home.main.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message")
    public void handleMessage(ChatMessageRequest request) {
        // 메시지 저장
        chatService.saveMessage(request.getRoomId(), request.getWriterName(), request.getContent());

        // 메시지 응답 DTO 생성
        ChatMessageResponse response = ChatMessageResponse.builder()
                .roomId(request.getRoomId())
                .writerName(request.getWriterName())
                .content(request.getContent())
                .sendTime(LocalDateTime.now())
                .build();

        // 구독 중인 클라이언트들에게 메시지 전송
        messagingTemplate.convertAndSend("/topic/room." + request.getRoomId(), response);
    }

}
