package com.ll.chat_pr.domain.home.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

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
@Getter
public class ChatRoomDto {
    long id;
    String name;
    LocalDateTime createDate;
    LocalDateTime modifyDate;

    public ChatRoomDto(String name) {
        this.id = ChatMessageIdGenerator.genNextId();
        this.name = name;
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
    }

    static class ChatMessageIdGenerator {
        private static long id = 0;
        public static long genNextId() {
            return ++id;
        }
    }
}
