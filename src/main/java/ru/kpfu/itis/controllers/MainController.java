package ru.kpfu.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import ru.kpfu.itis.models.BattleField;
import java.net.URL;
import java.util.ResourceBundle;

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

        //создание игрового поля для пользователя
        BattleField myBattleField = new BattleField(true,
                anchorPane,
                xForLeftField,
                yForLeftField,
                heightOfField,
                widthOfField);

        //создание игрового поля врага для пользователя
        BattleField enemyBattleField = new BattleField(false,
                anchorPane,
                xForLeftField + gapWithinFields + myBattleField.getWidth(),
                yForLeftField,
                heightOfField,
                widthOfField);

//        Ship ship = Ship.builder()
//                .loсationOfShipBowCellX(2)
//                .loсationOfShipBowCellY(2)
//                .sizeClassOfShip(1)
//                .sternLocation(0)
//                .build();
//        addShip(ship);
    }

//    private void addShip(Ship ship) {
//        Circle shipForm = new Circle(3, Color.BLACK);
//        GridPane.setConstraints(shipForm, ship.getLoсationOfShipBowCellX(), ship.getLoсationOfShipBowCellY());
//        gridPane.getChildren().add(shipForm);
//    }
}
