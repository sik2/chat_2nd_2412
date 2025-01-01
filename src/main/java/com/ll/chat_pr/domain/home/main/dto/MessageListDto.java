package com.ll.chat_pr.domain.home.main.dto;

import com.ll.chat_pr.global.jpa.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MessageListDto {
    private List<ChatMessageDto> messages;
    private Integer totalCount;
}
