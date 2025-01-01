package com.ll.chat_pr.domain.home.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.ll.chat_pr.domain.home.main.dto
 * fileName       : ChatRoom
 * author         : sungjun
 * date           : 2025-01-01
 * description    : 자동 주석 생성
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-01        kyd54       최초 생성
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatRoomDto {
    private long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<ChatMessageDto> chatMessages;  // 채팅방에 속한 메시지 리스트 추가

    public ChatRoomDto(String name) {
        this.id = ChatMessageIdGenerator.genNextId();
        this.name = name;
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
        this.chatMessages = new ArrayList<>();
    }

    static class ChatMessageIdGenerator {
        private static long id = 0;
        public static long genNextId() {
            return ++id;
        }
    }
}
