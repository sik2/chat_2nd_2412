package com.ll.chat_pr.domain.home.main.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.ll.chat_pr.domain.home.main.dto
 * fileName       : ChatMessageDto
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
@ToString
public class ChatMessageDto {
    private long id;
    private long chatRoomId;
    private String writerName;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public ChatMessageDto(long chatRoomId, String writerName, String content) {
        this.id = ChatMessageIdGenerator.genNextId();
        this.chatRoomId = chatRoomId;
        this.writerName = writerName;
        this.content = content;
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
