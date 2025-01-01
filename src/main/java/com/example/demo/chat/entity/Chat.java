package com.example.demo.chat.entity;

import com.example.demo.room.entity.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String authorName;
    private String content;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Chat(String authorName, String content, Room room) {
        this.authorName = authorName;
        this.content = content;
        this.room = room;
    }
}
