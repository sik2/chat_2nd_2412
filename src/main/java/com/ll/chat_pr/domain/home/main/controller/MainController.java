package com.ll.chat_pr.domain.home.main.controller;

import com.ll.chat_pr.domain.chatRoom.entity.ChatRoom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String goToChatRoomList() {
        return "redirect:/chat/room/list";
    }
}
