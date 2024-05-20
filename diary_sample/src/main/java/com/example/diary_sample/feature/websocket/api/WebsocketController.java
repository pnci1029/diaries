package com.example.diary_sample.feature.websocket.api;

import com.example.diary_sample.feature.websocket.dto.StatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/websocket")
public class WebsocketController {

    private final SimpMessageSendingOperations template;

    @MessageMapping("/message")
    public void receiveMessage(@RequestBody StatusDto chat) {
        // 메시지를 해당 채팅방 구독자들에게 전송 은 무슨
        template.convertAndSend("/sub/part/1", chat);
    }
}
