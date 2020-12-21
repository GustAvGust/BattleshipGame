package ru.kpfu.itis.sockets;

import ru.kpfu.itis.helpers.Launcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainForServer {
    public static EchoServerSocket firstSocket;
    public static EchoServerSocket secondSocket;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(7777);
            while (true) {
                Socket client = server.accept();
                if (firstSocket == null) {
                    firstSocket = new EchoServerSocket(client, 1);
                } else {
                    secondSocket = new EchoServerSocket(client, 2);
                    Launcher.updateEnemy();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
