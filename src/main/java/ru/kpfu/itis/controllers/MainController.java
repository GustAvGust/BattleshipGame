package ru.kpfu.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import ru.kpfu.itis.helpers.Launcher;
import ru.kpfu.itis.models.BattleField;
import ru.kpfu.itis.sockets.ReceiveMessageTask;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Double xForLeftField = 100.0;
        Double yForLeftField = 50.0;
        Double gapWithinFields = 100.0;
        Double heightOfField = 300.0;
        Double widthOfField = 300.0;

        //запускаем слушателя сообщений
        ReceiveMessageTask receiveMessageTask = new ReceiveMessageTask(Launcher.client.getFromServer(), this);
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(receiveMessageTask);

        //создание игрового поля для пользователя
        BattleField myBattleField = new BattleField(false,
                Launcher.client.getShips(),
                true,
                anchorPane,
                xForLeftField,
                yForLeftField,
                heightOfField,
                widthOfField);


        //создание игрового поля врага для пользователя
        BattleField enemyBattleField = new BattleField(false,
                null,
                false,
                anchorPane,
                xForLeftField + gapWithinFields + myBattleField.getWidth(),
                yForLeftField,
                heightOfField,
                widthOfField);
    }
}
