package com.ll.chat_pr.domain.home.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponseDto {
    private Long roomId;
    private String writerName;
    private String content;

}
