package ru.kpfu.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.kpfu.itis.helpers.Launcher;
import ru.kpfu.itis.helpers.SceneSwitcher;
import ru.kpfu.itis.models.BattleField;
import ru.kpfu.itis.sockets.MainForServer;
import ru.kpfu.itis.sockets.SocketClient;

import java.net.URL;
import java.util.ResourceBundle;

public class ShipsPlacementWindowController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button readyBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SocketClient client = new SocketClient("localhost", 7777);
        if (MainForServer.secondSocket == null) {
            client.setClientSocketId(1);
        } else {
            client.setClientSocketId(2);
        }
        Launcher.client = client;

        Double xForLeftField = 150.0;
        Double yForLeftField = 50.0;
        Double heightOfField = 300.0;
        Double widthOfField = 300.0;

        BattleField editBattleField = new BattleField(true,
                null,
                false,
                anchorPane,
                xForLeftField,
                yForLeftField,
                heightOfField,
                widthOfField);

        readyBtn.setOnAction(event -> {
            client.setShips(editBattleField.getShipsOnField());
            client.sendMessage("ready");
            SceneSwitcher.switchTo("/fxml/Main.fxml");
        });
    }
}
