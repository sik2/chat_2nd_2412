package com.ll.chat_pr.domain.home.main.controller;

import com.ll.chat_pr.domain.home.main.dto.ChatMessageDto;
import com.ll.chat_pr.domain.home.main.dto.MessageResponseDto;
import com.ll.chat_pr.domain.home.main.dto.MessageListDto;
import com.ll.chat_pr.domain.home.main.Sse.SseEmitters;
import com.ll.chat_pr.domain.home.main.service.ChatMessageService;
import com.ll.chat_pr.domain.home.main.service.ChatRoomService;
import com.ll.chat_pr.global.jpa.ChatMessage;
import com.ll.chat_pr.global.jpa.ChatRoom;
import com.ll.chat_pr.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final SseEmitters sseEmitters;


    @GetMapping("/")
    public String goToChatRoomList() {
        return "redirect:/chat/room/list";
    }

    @GetMapping("/chat/room/list")
    public String chatRoomList(Model model) {
        List<ChatRoom> chatRooms = chatRoomService.findAllChatRooms();
        model.addAttribute("chatRooms", chatRooms);
        return "domain/chat/chatRoom/list";
    }

    @GetMapping("/chat/room/make")
    public String chatRoomMake() {
        return "domain/chat/chatRoom/make";
    }

    @PostMapping("/chat/room/make")
    public String chatRoomMake(@RequestParam("name") String roomName) {
        Long id=chatRoomService.createChatRoom(roomName);
        return "redirect:/chat/room/"+ id;
    }

    @GetMapping("/chat/room/{id}")
    public String goChatRoom(@PathVariable Long id, Model model) {
        model.addAttribute("roomId", id);
        ChatRoom chatRoom = chatRoomService.findChatRoom(id);
        model.addAttribute("room", chatRoom);
        return "domain/chat/chatRoom/room";
    }

    @PostMapping("/chat/room/{roomId}/write")
    @ResponseBody
    public RsData<MessageResponseDto> writeMessage(@PathVariable("roomId") Long roomId,
                                                   @RequestBody MessageResponseDto messageResponseDto) {
        chatMessageService.writeMessage(roomId, messageResponseDto.getWriterName(), messageResponseDto.getContent());
        sseEmitters.noti("chat__messageAdded");

        return RsData.of("200", "메시지 작성 성공",
                new MessageResponseDto(roomId, messageResponseDto.getWriterName(), messageResponseDto.getContent()));
    }

    @GetMapping("/chat/room/{roomId}/messagesAfter/{afterId}")
    @ResponseBody
    public RsData<MessageListDto> getMessages(@PathVariable Long roomId,
                                               @PathVariable Long afterId) {
        ChatRoom chatRoom = chatRoomService.findChatRoom(roomId);

        List<ChatMessage> list=chatRoom.getChatMessages();

        List<ChatMessageDto> dtoList=
        list.stream()
                .map((msg)->new ChatMessageDto(msg.getId(),msg.getChatRoom().getId(),msg.getWriterName(),msg.getContent()))
                .collect(Collectors.toList());

        int idx=
                IntStream.range(0,list.size())
                .filter(
                (i)-> list.get(i).getId().equals(afterId)
                ).findFirst().orElse(-1);

        if(idx!=-1){
            dtoList=dtoList.subList(idx+1,dtoList.size());
        }

        return RsData.of("200", "메시지 조회 성공",
                new MessageListDto(dtoList, dtoList.size()));

    }



}
