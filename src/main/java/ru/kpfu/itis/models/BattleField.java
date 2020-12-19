package ru.kpfu.itis.models;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import ru.kpfu.itis.helpers.CellTexture;
import ru.kpfu.itis.helpers.CellTextureAdder;

import java.util.ArrayList;

@Getter
public class BattleField {
    // true если поле игрока, false - поле противника
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

        CellTextureAdder cellTextureAdder = new CellTextureAdder(gridPane);

        for (int i = 0; i < CELLSNUM; i++) {
            for (int j = 0; j < CELLSNUM; j++) {
                //размеры клетки
                Double textureHeight = 30.0;
                Double textureWidth = 30.0;

                //создание клетки с текстурой
                CellTexture seaCellTexture = new CellTexture("/img/water.png", textureHeight, textureWidth);

                //добавление клетки в (i, j) ячейку сетки
                cellTextureAdder.addTextureOnGridPane(seaCellTexture, i, j);

                seaCellTexture.setOnMouseClicked(event -> {
                    //новая клетка с другой картинкой
                    CellTexture afterClickCellTexture = new CellTexture("/img/water_dark.png", textureHeight, textureWidth);

                    //старые координаты клетки
                    Integer cellX = seaCellTexture.getCellX();
                    Integer cellY = seaCellTexture.getCellY();

                    cellTextureAdder.addTextureOnGridPane(afterClickCellTexture, cellX, cellY);
                });
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
