package com.example.game.config;

import com.example.game.socket.SocketServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketServerConfig {

    @Bean
    public CommandLineRunner socketServerRunner() {
        return args -> {
            SocketServer server = new SocketServer();
            server.start(6666);
        };
    }
}
