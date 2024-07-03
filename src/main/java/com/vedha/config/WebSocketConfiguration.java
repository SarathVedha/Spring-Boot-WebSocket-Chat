package com.vedha.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.*;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private final ObjectMapper objectMapper;

    // Configure message broker
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/topic", "/specific"); // Enables a simple in-memory broker, which is used to deliver messages to clients that are subscribed to a destination prefixed with "/topic" and "/specific" by client
        registry.setApplicationDestinationPrefixes("/app"); // Defines the prefix for messages that are bound for methods annotated with @MessageMapping and a client will send messages to this prefix
        registry.setUserDestinationPrefix("/specific"); // Used to prefix destinations for messages that are bound for a specific user
    }

    // Register STOMP endpoints
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws").withSockJS(); // Registers the "/ws" endpoint, enabling SockJS fallback options
    }

    // Configure message converters
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {

        messageConverters.add(new StringMessageConverter());
        messageConverters.add(new ByteArrayMessageConverter());

        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(APPLICATION_JSON);

        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setObjectMapper(objectMapper);
        messageConverter.setContentTypeResolver(resolver);

        messageConverters.add(messageConverter);

        return false;
    }
}
