package ru.kpfu.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ru.kpfu.itis.models.Ship;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        renderField();
        Ship ship = Ship.builder()
                .loсationOfShipBowCellX(2)
                .loсationOfShipBowCellY(2)
                .sizeClassOfShip(1)
                .sternLocation(0)
                .build();
        addShip(ship);
    }

    private void addShip(Ship ship) {
        Circle shipForm = new Circle(3, Color.BLACK);
        GridPane.setConstraints(shipForm, ship.getLoсationOfShipBowCellX(), ship.getLoсationOfShipBowCellY());
        gridPane.getChildren().add(shipForm);
    }

    private void renderField() {
        for (int i = 0; i < gridPane.getRowConstraints().size(); i++) {
            for (int j = 0; j < gridPane.getColumnConstraints().size(); j++) {
                //создание кнопки без текста с картинкой
                ImageView cellTexture = new ImageView("/img/water.png");

                //размеры текстуры
                cellTexture.setFitHeight(30.0);
                cellTexture.setFitWidth(30.0);

                //добавление кнопки в (j, i) ячейку сетки
                GridPane.setConstraints(cellTexture, j, i);

                //вывод координаты при нажатии на клетку
                cellTexture.setOnMouseClicked(event -> {
                    label.setText("(" + GridPane.getColumnIndex(cellTexture) + ", " + GridPane.getRowIndex(cellTexture) + ")");

                    //новая текстура
                    ImageView cellTextureAfterClick = new ImageView("/img/water_dark.png");

                    //размеры текстуры
                    cellTextureAfterClick.setFitHeight(30.0);
                    cellTextureAfterClick.setFitWidth(30.0);

                    Integer cellX = GridPane.getColumnIndex(cellTexture);
                    Integer cellY = GridPane.getRowIndex(cellTexture);

                    //удаление старой текстуры
                    GridPane.clearConstraints(cellTexture);

                    GridPane.setConstraints(cellTextureAfterClick, cellX, cellY);
                    gridPane.getChildren().add(cellTextureAfterClick);
                });

                gridPane.getChildren().add(cellTexture);
            }
        }
    }
}
