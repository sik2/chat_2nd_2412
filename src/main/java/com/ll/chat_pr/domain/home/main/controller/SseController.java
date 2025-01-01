package com.ll.chat_pr.domain.home.main.controller;

import com.ll.chat_pr.domain.home.main.Sse.SseEmitters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Controller
@RequestMapping("/sse")
@RequiredArgsConstructor
public class SseController {
    //SSE 연결의 관리자. 이건 bean으로 등록되어 있음
    private final SseEmitters emitters;

    //SSE 연결을 위한 endpoint. SSE 연결을 담당한다.
    @GetMapping(value="/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(){
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);

        try{
            //SSE로 연결된 클라에게 초기 성공 메시지를 클라에게 보낸다.
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected!")
            );
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(emitter);
    }
}