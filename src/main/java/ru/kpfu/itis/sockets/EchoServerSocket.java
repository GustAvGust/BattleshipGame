package ru.kpfu.itis.sockets;

import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.helpers.Launcher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

@Getter
@Setter
public class EchoServerSocket extends Thread {

    private Integer clientSocketId;
    private Socket client;
    private BufferedReader fromClient;
    private PrintWriter toClient;

    public EchoServerSocket(Socket client, Integer clientSocketId) {
        this.client = client;
        this.clientSocketId = clientSocketId;
        try {
            this.fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.toClient = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        start();
    }

    @Override
    public void run() {
        try {
            // читаем сообщения от клиента
            String messageFromClient = fromClient.readLine();
            while (messageFromClient != null) {
                EchoServerSocket serverSocket = Launcher.enemyServerSocket;
                serverSocket.send(messageFromClient);
                messageFromClient = fromClient.readLine();
            }
        } catch (
                IOException e) {
            throw new IllegalStateException(e);
        }

    }

    private void send(String messageFromClient) {
        toClient.println(messageFromClient);
    }
}
