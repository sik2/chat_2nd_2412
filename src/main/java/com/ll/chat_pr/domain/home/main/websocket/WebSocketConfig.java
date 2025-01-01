package com.ll.chat_pr.domain.home.main.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

//Endpoint란 API가 서버에서 자원(resource)에 접근할 수 있도록 하는 URL

@Configuration
@EnableWebSocketMessageBroker
//보류
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //노출할 endpoint를 설정(sockJS 이용)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //웹소켓의 핸드셰이크를 위한 엔드포인트
       registry.addEndpoint("/ws")
               .setAllowedOriginPatterns("*")
               .withSockJS(); //SockJS는 WebSocket을 지원하지 않는 브라우저에서 HTTP의 Polling과 같은 방식으로 WebSocket의 요청을 수행하도록 도와준다.
    }

    //메시지브로커 설정(
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //서버가 클라이언트에게 주는 메시지의 엔드포인트 (클라이언트는 구독자고 서버는 퍼블리셔.)
        //sub으로 시작되는 request을 구독(관찰)한 모든 사용자들에게 메시지를 broadcast하게 된다.
        registry.enableSimpleBroker("/sub");

        //클라이언트가 서버에게 주는 메시지의 엔드포인트
        //pub로 시작되는 메시지는 message-handling methods로 라우팅된다는 의미다.
        registry.setApplicationDestinationPrefixes("/pub");
    }
}
