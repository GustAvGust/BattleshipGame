package ru.kpfu.itis.models;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import ru.kpfu.itis.helpers.CellTexture;
import ru.kpfu.itis.helpers.CellTextureAdder;
import java.util.Arrays;

@Getter
public class BattleField {
    private static final Integer CELLSNUM = 10;
    //размеры клетки
    private static Double textureHeight = 30.0;
    private static Double textureWidth = 30.0;

    // true - если поле игрока, false - поле противника
    private Boolean isYourselfBattleField;
    private Boolean isEditModeField;
    private Boolean[][] shipsOnField;
    private GridPane gridPane;
    private CellTextureAdder cellTextureAdder;

    public BattleField(Boolean isEditModeField, Boolean[][] shipsOnField, Boolean isYourselfBattleField, AnchorPane anchorPane, Double xForField, Double yForField, Double heightOfField, Double widthOfField) {
        this.isYourselfBattleField = isYourselfBattleField;
        this.isEditModeField = isEditModeField;

        if (isEditModeField) {
            this.shipsOnField = new Boolean[CELLSNUM][CELLSNUM];
            for (Boolean[] booleans : this.shipsOnField) {
                Arrays.fill(booleans, false);
            }
        } else {
            this.shipsOnField = shipsOnField;
        }

        renderField(anchorPane, xForField, yForField, heightOfField, widthOfField);
    }

    private void renderField(AnchorPane anchorPane, Double xForField, Double yForField, Double heightOfField, Double widthOfField) {
        this.gridPane = new GridPane();
        //задание размеров gridPane
        gridPane.setPrefHeight(heightOfField);
        gridPane.setPrefWidth(widthOfField);

        this.cellTextureAdder = new CellTextureAdder(gridPane);

        for (int i = 0; i < CELLSNUM; i++) {
            for (int j = 0; j < CELLSNUM; j++) {
                //создание клетки с текстурой
                CellTexture seaCellTexture = new CellTexture("/img/water.png", textureHeight, textureWidth);

                //добавление клетки в (i, j) ячейку сетки
                cellTextureAdder.addTextureOnGridPane(seaCellTexture, i, j);

                //игрок не может нажимать на клетки своего поля
                if (!isYourselfBattleField) {
                    seaCellTexture.setOnMouseClicked(event -> {
                        //новая клетка с другой картинкой
                        CellTexture afterClickCellTexture = new CellTexture("/img/water_dark.png", textureHeight, textureWidth);

                        //старые координаты клетки
                        Integer cellX = seaCellTexture.getCellX();
                        Integer cellY = seaCellTexture.getCellY();

                        cellTextureAdder.addTextureOnGridPane(afterClickCellTexture, cellX, cellY);
                    });
                }

                //поле для расстановки кораблей
                if (isEditModeField) {
                    seaCellTexture.setOnMouseClicked(event -> {
                        //старые координаты клетки
                        Integer cellX = seaCellTexture.getCellX();
                        Integer cellY = seaCellTexture.getCellY();

                        Ship ship = Ship.builder()
                                .loсationOfShipBowCellX(cellX)
                                .loсationOfShipBowCellY(cellY)
                                .sizeClassOfShip(1)
                                .sternLocation(0)
                                .build();

                        addShip(ship);
                    });
                }
            }
        }

        //добавление gridPane на anchorPane
        AnchorPane.setTopAnchor(gridPane, yForField);
        AnchorPane.setLeftAnchor(gridPane, xForField);
        anchorPane.getChildren().add(gridPane);

        if (isYourselfBattleField) {
            for (int i = 0; i < shipsOnField.length; i++) {
                for (int j = 0; j < shipsOnField[i].length; j++)
                    if (shipsOnField[i][j]) {
                        showShip(i, j);
                    }
            }

        }
    }

    public Double getWidth() {
        return this.gridPane.getPrefWidth();
    }

    private void addShip(Ship ship) {
        if (shipMayBeLocated(ship)) {
            shipsOnField[ship.getLoсationOfShipBowCellX()][ship.getLoсationOfShipBowCellY()] = true;
            showShip(ship.getLoсationOfShipBowCellX(), ship.getLoсationOfShipBowCellY());
        }
    }

    private void showShip(Integer x, Integer y) {
        Rectangle shipForm = new Rectangle(15, 15, Color.BLACK);
        GridPane.setConstraints(shipForm, x, y);
        gridPane.getChildren().add(shipForm);
    }

    private boolean shipMayBeLocated(Ship ship) {
        Integer newShipCellX = ship.getLoсationOfShipBowCellX();
        Integer newShipCellY = ship.getLoсationOfShipBowCellY();
        Integer checkedCellX;
        Integer checkedCellY;


        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                checkedCellX = newShipCellX + i;
                checkedCellY = newShipCellY + j;
                if (checkedCellX < 0 || checkedCellY < 0 || checkedCellX >= CELLSNUM || checkedCellY >= CELLSNUM || (i == 0 && j == 0)) {
                    continue;
                }
                if (shipsOnField[checkedCellX][checkedCellY]) {
                    return false;
                }
            }
        }
        return true;
    }

}
