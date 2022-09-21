package com.reactive.server;

import com.reactive.server.socket.EventServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        EventServer eventServer = new EventServer();
        eventServer.start();
    }
}
