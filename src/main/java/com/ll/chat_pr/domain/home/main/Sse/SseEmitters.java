package com.ll.chat_pr.domain.home.main.Sse;

import com.ll.chat_pr.domain.home.main.Utility.Ut;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
//Emitter들을 관리. SSE 연결 하나하나를 관리한다는 말이다.
public class SseEmitters {
    //CopyOnWriteArrayList: 동기화된 리스트. 쓰기 작업이 일어날 때마다 새로운 리스트를 생성하고, 읽기 작업은 이전 리스트를 참조한다.
    //Thread-safe한 List를 사용한다는 말이다.
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter add(SseEmitter emitter){
        this.emitters.add(emitter);

        //클라 연결이 완료되면 관리 대상에서 제거하도록 이벤트를 달아둠
        emitter.onCompletion(()->this.emitters.remove(emitter));

        //클라에게 타임아웃이 발생하면 클라를 complete하도록 이벤트를 달아둠
        emitter.onTimeout(()->emitter.complete());

        return emitter;
    }

    // 데이터 없이 이벤트 이름만으로 알림을 보내는 간편 메서드
    public void noti(String eventName){
        noti(eventName, Ut.mapOf());
    }

    // 모든 연결된 클라이언트들에게 이벤트를 전송하는 메서드
    public void noti(String eventName, Map<String, Object> data){
        //등록된 모든 SSE 연결에 대해 이벤트를 전달 (옵저버 패턴?)
        emitters.forEach(emitter->{
            try{
                emitter.send(SseEmitter.event()
                        .name(eventName)
                        .data(data)
                );
            }catch (ClientAbortException e){
                //클라가 브라우저를 끈 경우로, 무시
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        });
    }
}