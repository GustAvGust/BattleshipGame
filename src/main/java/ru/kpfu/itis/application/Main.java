package ru.kpfu.itis.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.kpfu.itis.helpers.SceneSwitcher;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneSwitcher.init(primaryStage);

        String startMenuFxmlFile = "/fxml/StartMenu.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(startMenuFxmlFile));

        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
