package ru.kpfu.itis.models;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class BattleField {
    private Boolean isYourselfBattleField;
    private ArrayList<Ship> shipsOnField;
    private GridPane gridPane;
    private static final Integer CELLSNUM = 10;

    public BattleField(Boolean isYourselfBattleField, AnchorPane anchorPane, Double xForField, Double yForField, Double heightOfField, Double widthOfField) {
        this.isYourselfBattleField = isYourselfBattleField;
        renderField(anchorPane, xForField, yForField, heightOfField, widthOfField);
    }

    private void renderField(AnchorPane anchorPane, Double xForField, Double yForField, Double heightOfField, Double widthOfField) {
        this.gridPane = new GridPane();

        //задание размеров gridPane
        gridPane.setPrefHeight(heightOfField);
        gridPane.setPrefWidth(widthOfField);

        for (int i = 0; i < CELLSNUM; i++) {
            for (int j = 0; j < CELLSNUM; j++) {
                //создание кнопки без текста с картинкой
                //далее именнуемой "клетка"
                ImageView cellTexture = new ImageView("/img/water.png");

                //размеры клетки
                cellTexture.setFitHeight(30.0);
                cellTexture.setFitWidth(30.0);

                //добавление клетки в (i, j) ячейку сетки
                GridPane.setConstraints(cellTexture, i, j);

                cellTexture.setOnMouseClicked(event -> {
                    //вывод координаты при нажатии на клетку
//                    label.setText("(" + GridPane.getColumnIndex(cellTexture) + ", " + GridPane.getRowIndex(cellTexture) + ")");

                    //новая клетка с другой картинкой
                    ImageView cellTextureAfterClick = new ImageView("/img/water_dark.png");

                    //размеры клетки
                    cellTextureAfterClick.setFitHeight(30.0);
                    cellTextureAfterClick.setFitWidth(30.0);

                    //старые координаты клетки
                    Integer cellX = GridPane.getColumnIndex(cellTexture);
                    Integer cellY = GridPane.getRowIndex(cellTexture);

                    //удаление старой клетки
                    GridPane.clearConstraints(cellTexture);

                    //добавление новой клетки
                    GridPane.setConstraints(cellTextureAfterClick, cellX, cellY);
                    gridPane.getChildren().add(cellTextureAfterClick);
                });

                gridPane.getChildren().add(cellTexture);
            }
        }
        //добавление gridPane на anchorPane
        AnchorPane.setTopAnchor(gridPane, yForField);
        AnchorPane.setLeftAnchor(gridPane, xForField);
        anchorPane.getChildren().add(gridPane);
    }

    public Double getWidth() {
        return this.gridPane.getPrefWidth();
    }
}
