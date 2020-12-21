package ru.kpfu.itis.sockets;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Getter
@Setter
public class SocketClient {

    private Integer clientSocketId;
    private Socket client;
    private Boolean[][] ships;
    private PrintWriter toServer;
    private BufferedReader fromServer;

    public SocketClient(String host, int port) {
        try {
            client = new Socket(host, port);
            toServer = new PrintWriter(client.getOutputStream(), true);
            this.ships = ships;
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        toServer.println(message);
    }
}
