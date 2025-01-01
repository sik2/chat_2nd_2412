package com.example.demo.room.dto;

import com.example.demo.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoomResponse {
    private Long id;
    private String name;
    private int chatCount;
    private LocalDateTime createDate;

    // 엔티티 -> DTO 변환 메소드
    public RoomResponse(Room room) {
        this.id = room.getId();
        this.name =room.getName();
        this.chatCount = room.getChats().size();
        this.createDate = room.getCreateDate();
    }
}
