package com.ll.chat_pr.domain.home.main.controller;

import com.ll.chat_pr.domain.home.main.chatMessageDto.ChatMessageResponse;
import com.ll.chat_pr.domain.home.main.dto.ChatRoomListDto;
import com.ll.chat_pr.domain.home.main.dto.ChatRoomMakeRequest;
import com.ll.chat_pr.domain.home.main.entity.ChatRoom;
import com.ll.chat_pr.domain.home.main.service.ChatService;
import com.ll.chat_pr.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    //채팅방 목록 조회
    @GetMapping("/room/list")
    public RsData<List<ChatRoomListDto>> getChatRoomList(){
        return RsData.of("200", "채팅방 리스트입니다", chatService.getChatRoomList());
    }
    //채팅방 개설
    @PostMapping("/room/make")
    public RsData<ChatRoom> makeChatRoom(@RequestBody ChatRoomMakeRequest chatRoomReq){
        ChatRoom chatRoom = chatService.createChatRoom(chatRoomReq);

        return RsData.of("200", "채팅방이 생성되었습니다", chatRoom);

    }

    @GetMapping("/room/{roomId}")
    public RsData<ChatRoom> enterChatRoom(@PathVariable Long roomId){
        return RsData.of("200", "채팅방에 입장하였습니다", chatService.enterChatRoom(roomId));
    }

    @PostMapping("/room/{roomId}/messagesAfter/{afterId}")
    public RsData<List<ChatMessageResponse>> getMessagesAfter(@PathVariable Long roomId, @PathVariable Long afterId) {
        return RsData.of("200", "메시지를 조회하였습니다", chatService.getMessagesAfter(roomId, afterId));
    }

}
