package ru.kpfu.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import ru.kpfu.itis.helpers.SceneSwitcher;
import java.net.URL;
import java.util.ResourceBundle;

public class ShipsPlacementWindowController implements Initializable {

    @FXML
    private Button readyBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readyBtn.setOnAction(event -> SceneSwitcher.switchTo("/fxml/Main.fxml"));
    }
}
