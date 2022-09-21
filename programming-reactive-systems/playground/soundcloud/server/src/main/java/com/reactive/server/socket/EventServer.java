package com.reactive.server.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class EventServer {

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 9090));
        System.out.println("event server started");

        try (Socket socket = serverSocket.accept();
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            while (true) {
                String msg = input.readUTF(); // reading a message
                output.writeUTF(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
