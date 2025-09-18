package com.pubsub.publisher.controller;

import com.pubsub.publisher.dto.UserMessageDTO;
import com.pubsub.publisher.model.UserMessage;
import com.pubsub.publisher.publishers.HelloPubSubPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class HelloPubSubController {

    private final HelloPubSubPublisher publisher;

    @PostMapping("/publish")
    public Mono<String> publish(@RequestBody UserMessageDTO userMessageDTO) {
        log.info("Received a POST at /v1/publish with message: {}", userMessageDTO);

        UserMessage userMessage = new UserMessage(userMessageDTO.body(), userMessageDTO.username(), LocalDateTime.now());

        publisher.publish(userMessage);

        return  Mono.just("Message published");
    }
}
