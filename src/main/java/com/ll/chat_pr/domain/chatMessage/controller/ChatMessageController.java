package com.ll.chat_pr.domain.chatMessage.controller;

import com.ll.chat_pr.domain.chatMessage.entity.ChatMessage;
import com.ll.chat_pr.domain.chatMessage.service.ChatMessageService;
import com.ll.chat_pr.global.rsData.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/room/{roomId}/")
public class ChatMessageController {
  private final ChatMessageService chatMessageService;
  private final SimpMessagingTemplate messagingTemplate;

  @PostMapping("/write")
  public RsData write(
      @PathVariable Long roomId,
      @RequestBody WriteRequestBody requestBody
  ) {
    ChatMessage chatMessage = chatMessageService.write(roomId, requestBody.getWriterName(), requestBody.getContent());

    // 웹소켓을 통해 채팅방의 모든 사용자에게 새 메시지를 브로드캐스트
    messagingTemplate.convertAndSend("/topic/chat/room/" + roomId, chatMessage);

    return RsData.of("200", "메시지가 작성되었습니다.");
  }

  @GetMapping("/messagesAfter/{afterId}")
  public RsData<MessagesAfterResponseBody> messagesAfter(
      @PathVariable Long roomId,
      @PathVariable Long afterId
  ) {
    List<ChatMessage> messages = chatMessageService.findByRoomIdAndIdAfter(roomId, afterId);

    return RsData.of(
        "200",
        "성공",
        new MessagesAfterResponseBody(messages)
    );
  }

  @Getter
  public static class WriteRequestBody {
    private String writerName;
    private String content;
  }

  @AllArgsConstructor
  @Getter
  public static class MessagesAfterResponseBody {
    private List<ChatMessage> messages;
  }
}
