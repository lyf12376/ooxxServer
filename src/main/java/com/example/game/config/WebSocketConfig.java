package com.example.game.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .withSockJS()
                .setInterceptors(new org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor() {
                    @Override
                    public void afterHandshake(org.springframework.http.server.ServerHttpRequest request,
                                               org.springframework.http.server.ServerHttpResponse response,
                                               org.springframework.web.socket.WebSocketHandler wsHandler,
                                               Exception ex) {
                        logger.info("New WebSocket connection from: {}", request.getRemoteAddress());
                        super.afterHandshake(request, response, wsHandler, ex);
                    }
                });
    }
}


