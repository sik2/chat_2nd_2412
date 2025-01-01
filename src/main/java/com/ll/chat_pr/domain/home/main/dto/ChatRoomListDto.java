package com.ll.chat_pr.domain.home.main.dto;

import com.ll.chat_pr.domain.home.main.entity.ChatRoom;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ChatRoomListDto {
    private Long roomId;
    private String roomName;

     public static ChatRoomListDto of(ChatRoom chatRoom) {
        return ChatRoomListDto.builder()
                .roomId(chatRoom.getId())
                .roomName(chatRoom.getRoomName())
                .build();
    }
}
