package ru.kpfu.itis.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

@Getter
public class SceneSwitcher {
    private static Stage stage;

    public static void init(Stage stage) {
        SceneSwitcher.stage = stage;
    }

    public static void switchTo(String url) {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(url));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SceneSwitcher.stage.setScene(new Scene(root));
        SceneSwitcher.stage.setResizable(false);
        SceneSwitcher.stage.show();
    }

    public static void switchOff() {
        SceneSwitcher.stage.close();
    }
}
