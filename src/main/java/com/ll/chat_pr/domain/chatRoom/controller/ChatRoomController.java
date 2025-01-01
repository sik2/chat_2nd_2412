package com.ll.chat_pr.domain.chatRoom.controller;

import com.ll.chat_pr.domain.chatRoom.entity.ChatRoom;
import com.ll.chat_pr.domain.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat/room")
@RequiredArgsConstructor
public class ChatRoomController {
  private final ChatRoomService chatRoomService;

  @GetMapping("list")
  public String chatRoomList(Model model) {
    model.addAttribute("chatRooms", chatRoomService.findAll());
    return "chat/room/list";
  }

  @GetMapping("make")
  public String makeChatRoomForm() {
    return "chat/room/make";
  }

  @PostMapping("make")
  public String makeChatRoom(@RequestParam String name) {
    ChatRoom chatRoom = chatRoomService.makeChatRoom(name);
    return "redirect:/chat/room/list";
  }

  @GetMapping("{id}")
  public String showRoom(@PathVariable Long id, Model model) {
    ChatRoom chatRoom = chatRoomService.findById(id);
//    List<ChatMessage> messages = chatMessageService.findByChatRoomId(id);

    // 3. 모델에 데이터 추가
    model.addAttribute("room", chatRoom);  // 채팅방 정보
//    model.addAttribute("messages", messages);   // 채팅 메시지들
    model.addAttribute("roomId", id);

    return "chat/room/room";
  }
}
