package ru.kpfu.itis.helpers;


import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lombok.Getter;

@Getter
public class CellTexture {
    private ImageView imageView;

    public CellTexture(String textureUrl, Double textureHeight, Double textureWidth) {
        //создание клетки с текстурой
        this.imageView = new ImageView(textureUrl);

        //размеры клетки
        imageView.setFitHeight(textureHeight);
        imageView.setFitWidth(textureWidth);
    }

    public Integer getCellX() {
        //для клетки (0, 0) getColumnIndex/getRowIndex возвращаю null
        return GridPane.getColumnIndex(this.imageView) == null ? 0 : GridPane.getColumnIndex(this.imageView);
    }

    public Integer getCellY() {
        //для клетки (0, 0) getColumnIndex/getRowIndex возвращаю null
        return GridPane.getRowIndex(this.imageView) == null ? 0 : GridPane.getRowIndex(this.imageView);
    }

    public void setOnMouseClicked(Action action) {
        imageView.setOnMouseClicked(action::action);
    }
}
