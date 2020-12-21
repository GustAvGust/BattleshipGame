package ru.kpfu.itis.sockets;

import javafx.concurrent.Task;
import ru.kpfu.itis.controllers.MainController;
import java.io.BufferedReader;
import java.io.IOException;

//слушатель сообщений с сервера
public class ReceiveMessageTask extends Task<Void> {
    private BufferedReader fromServer;
    private MainController mainController;

    public ReceiveMessageTask(BufferedReader fromServer, MainController mainController) {
        this.fromServer = fromServer;
        this.mainController = mainController;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    if (messageFromServer.equals("ready")) {
                       // mainController.createEnemyField();
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

