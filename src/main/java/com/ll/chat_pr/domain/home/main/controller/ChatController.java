package com.ll.chat_pr.domain.home.main.controller;

import com.ll.chat_pr.domain.home.main.dto.ChatMessageDto;
import com.ll.chat_pr.domain.home.main.dto.ChatRoomDto;
import com.ll.chat_pr.global.rsData.RsData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * packageName    : com.ll.chat_pr.domain.home.main.controller
 * fileName       : ChatController
 * author         : sungjun
 * date           : 2025-01-01
 * description    : 자동 주석 생성
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-01        kyd54       최초 생성
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    // 로거
    Logger logger = Logger.getLogger("ChatController");

    // 채팅방 목록
    private List<ChatRoomDto> chatRooms = new ArrayList<>();

    private List<ChatMessageDto> chatMessages = new ArrayList<>();

    //  GET /chat/room/list : 채팅방 목록 조회
    @GetMapping("/room/list")
    public String ChatRoomListPage(Model model) {
        // 메서드 호출 확인용 로그
        logger.info("채팅방 목록 조회");

        // HTML에 전달할 데이터
        model.addAttribute("chatRooms", chatRooms);

        return "domain/chat/chatRoom/list";
    }

    //  GET /chat/room/make : 채팅방 생성 페이지
    @GetMapping("/room/make")
    public String ChatRoomMakePage() {
        return "domain/chat/chatRoom/make";
    }

    //  POST /chat/room/make : 채팅방 생성
    @PostMapping("/room/make")
    public String ChatRoomMake(@RequestParam String name) {
        // 메서드 호출 확인용 로그
        logger.info("채팅방 생성 요청");

        chatRooms.add(new ChatRoomDto(name));

        // 채팅방 생성 목록 확인용 로그
//        for (ChatRoomDto room : chatRooms) {
//            logger.info("채팅방 이름: " + room.getName() + ", 생성일: " + room.getCreateDate());
//        }

        
        return "redirect:/chat/room/list";
    }

    //  GET /chat/room/{roomId} : 채팅방 입장
    @GetMapping("/room/{roomId}")
    public String ChatRoomPage(@PathVariable("roomId") long roomId,
                               Model model) {
        logger.info("채팅방 입장");

        // roomId에 해당하는 채팅방 조회
        ChatRoomDto room = findChatRoomById(roomId);

        // HTML에 전달할 데이터
        model.addAttribute("roomId", roomId);
        if (room != null) {
            model.addAttribute("room", room);
        }

        return "domain/chat/chatRoom/room";
    }

    //  POST /chat/room/{roomId}/write : 메시지 작성
    @PostMapping("/room/{roomId}/write")
    @ResponseBody
    public RsData ChatRoomWrite(@PathVariable("roomId") long roomId,
                                @RequestBody ChatMessageDto chatMessageDto) {
        // 메서드 호출 확인용 로그
        logger.info("메시지 작성");

        // 채팅방에 메시지 추가
        String writerName = chatMessageDto.getWriterName();
        String content = chatMessageDto.getContent();
        ChatMessageDto newMessage = createAndAddMessageToRoom(roomId, writerName, content);
        chatMessages.add(newMessage);
        logger.info("새 메시지 생성됨");

        // RsData로 포장하여 반환
        return RsData.of("S-200", "메시지가 성공적으로 작성되었습니다.", newMessage);
    }

    //  GET /chat/room/{roomId}/messagesAfter/{afterId} : 채팅방 메시지 조회
    @GetMapping("/room/{roomId}/messagesAfter/{afterId}")
    @ResponseBody
    public RsData ChatRoomMessagesAfter(@PathVariable("afterId") long afterId,
                                        @PathVariable("roomId") long roomId) {
        // 메시지 조회 로직 (afterId 이후의 메시지들)
        List<ChatMessageDto> messages = chatMessages.stream()
                .filter(message -> message.getId() > afterId && message.getChatRoomId() == roomId)
                .toList();

        // 조회된 메시지 출력 (테스트용)
//        System.out.println("조회된 메시지 수: " + messages.size());
//        messages.forEach(message -> System.out.println("Message ID: " + message.getId() + ", Writer: " + message.getWriterName() + ", Content: " + message.getContent()));
//
//        System.out.println("Server Response: " + messages);

        // 메시지가 없다면 빈 리스트 반환
        if (messages.isEmpty()) {
            return RsData.of("S-200", "새로운 메시지가 없습니다.", messages);
        }

        // 메시지가 있으면 메시지 리스트 반환
        return RsData.of("S-200", "메시지를 성공적으로 조회했습니다.", messages);
    }


    // 현재 채팅방에 메시지 추가
    private ChatMessageDto createAndAddMessageToRoom(long roomId, String writerName, String content) {
        ChatMessageDto newMessage = new ChatMessageDto(roomId, writerName, content);

        // 채팅방을 찾고 메시지 추가
        ChatRoomDto chatRoom = findChatRoomById(roomId);
        if (chatRoom != null) {
            chatRoom.getChatMessages().add(newMessage);
        } else {
            throw new IllegalArgumentException("존재하지 않는 채팅방입니다: roomId=" + roomId);
        }

        return newMessage;
    }

    // roomId로 채팅방 찾기
    private ChatRoomDto findChatRoomById(long roomId) {
        // 기존 채팅방 리스트에서 ID로 검색
        return chatRooms.stream()
                .filter(room -> room.getId() == roomId)
                .findFirst()
                .orElse(null);
    }
}
