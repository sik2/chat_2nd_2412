package com.example.demo.config;

import com.example.demo.room.RoomRepository;
import com.example.demo.room.entity.Room;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(RoomRepository roomRepository) {
        return args -> {
            // 0번 Room이 존재하지 않으면 생성
            Room room1 = new Room("polling");
            roomRepository.save(room1);

            // 1번 Room이 존재하지 않으면 생성
            Room room2 = new Room("sse");
            roomRepository.save(room2);

            Room room3 = new Room("websocket");
            roomRepository.save(room3);
        };
    }
}