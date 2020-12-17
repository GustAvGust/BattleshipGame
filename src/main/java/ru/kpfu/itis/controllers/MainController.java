package ru.kpfu.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        renderField();
    }

    private void renderField() {
        for (int i = 0; i < gridPane.getRowConstraints().size(); i++) {
            for (int j = 0; j < gridPane.getColumnConstraints().size(); j++) {
                Button btn = new Button();
                GridPane.setConstraints(btn, j, i);

                //для того чтобы кнопка занима ячейку сетки целиком
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setMaxHeight(Double.MAX_VALUE);

                //вывод координаты при нажатии кнопки
                btn.setOnAction(event -> {
                    label.setText("(" + GridPane.getColumnIndex(btn) + ", " + GridPane.getRowIndex(btn) + ")");
                });

                gridPane.getChildren().add(btn);
            }
        }
    }
}
