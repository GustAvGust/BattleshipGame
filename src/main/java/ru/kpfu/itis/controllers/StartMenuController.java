package ru.kpfu.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import ru.kpfu.itis.helpers.SceneSwitcher;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {

    @FXML
    private Button startBtn;

    @FXML
    private Button exitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startBtn.setOnAction(event -> SceneSwitcher.switchTo("/fxml/ShipsPlacementWindow.fxml"));
        exitBtn.setOnAction(event -> SceneSwitcher.switchOff());
    }
}
