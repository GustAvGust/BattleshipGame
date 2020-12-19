package ru.kpfu.itis.helpers;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class CellTextureAdder {
    private GridPane gridPane;

    public CellTextureAdder(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void addTextureOnGridPane(CellTexture cellTexture, Integer cellX, Integer cellY) {
        Node oldCell = null;
        for (Node child : gridPane.getChildren()) {
            if (GridPane.getRowIndex(child) == cellY && GridPane.getColumnIndex(child) == cellX) {
                oldCell = child;
                break;
            }
        }

        //удаление старой клетки
        if (oldCell != null) {
            GridPane.clearConstraints(oldCell);
        }

        GridPane.setConstraints(cellTexture.getImageView(), cellX, cellY);
        gridPane.getChildren().add(cellTexture.getImageView());
    }
}
