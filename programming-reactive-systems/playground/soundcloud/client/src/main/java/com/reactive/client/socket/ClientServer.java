package com.reactive.client.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientServer {
    public void start() throws IOException {

        try (Socket socket = new Socket("localhost", 9090);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output  = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                Scanner scanner = new Scanner(System.in);
                String msg = scanner.nextLine();

                output.writeUTF(msg); // sending message to the server
                String receivedMsg = input.readUTF(); // response message
                System.out.println("Received from server: " + receivedMsg);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

