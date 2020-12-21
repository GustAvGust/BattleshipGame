package ru.kpfu.itis.helpers;

import ru.kpfu.itis.sockets.EchoServerSocket;
import ru.kpfu.itis.sockets.MainForServer;
import ru.kpfu.itis.sockets.SocketClient;

public class Launcher {
    public static SocketClient client;
    public static EchoServerSocket enemyServerSocket;

    public static void updateEnemy() {
        Launcher.enemyServerSocket = MainForServer.firstSocket.getClientSocketId() == Launcher.client.getClientSocketId() ?
                MainForServer.secondSocket : MainForServer.firstSocket;
    }
}
