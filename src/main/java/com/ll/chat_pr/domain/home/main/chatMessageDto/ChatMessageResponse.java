package com.ll.chat_pr.domain.home.main.chatMessageDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageResponse {
    private Long roomId;
    private String writerName;
    private String content;
    private LocalDateTime sendTime;
}