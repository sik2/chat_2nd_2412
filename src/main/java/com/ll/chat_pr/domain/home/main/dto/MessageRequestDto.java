package com.ll.chat_pr.domain.home.main.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {
    private String writerName;
    private String content;
}
