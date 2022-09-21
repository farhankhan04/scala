package com.reactive.client;

import com.reactive.client.socket.ClientServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        ClientServer clientServer = new ClientServer();
        clientServer.start();
    }
}
