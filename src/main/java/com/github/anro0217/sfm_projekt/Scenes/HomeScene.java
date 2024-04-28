package com.github.zdanielm.sfm_projekt.Scenes;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeScene extends Application {
    private Stage homeStage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        this.homeStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXMLHomeScene.fxml"));
        root = loader.load();
        scene = new Scene(root, 1920, 1080);
        homeStage.setScene(scene);

        Button searchButton = (Button) loader.getNamespace().get("SearchButton");
        Button monitoringButton = (Button) loader.getNamespace().get("MonitoringButton");
        Button quitButton = (Button) loader.getNamespace().get("QuitButton");

        int res = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        double scale = (double)res/96;
        double sceneWidth = scene.getWidth()/scale;
        double sceneHeight = scene.getHeight()/scale;

        searchButton.getStyleClass().add("button");
        monitoringButton.getStyleClass().add("button");
        quitButton.getStyleClass().add("button");
        searchButton.setFont(Font.font(sceneWidth * 0.02));
        monitoringButton.setFont(Font.font(sceneWidth * 0.02));
        quitButton.setFont(Font.font(sceneWidth * 0.02));
        ((HBox) searchButton.getParent()).setPadding(new Insets(sceneHeight * 0.25, 0, 0, sceneWidth * 0.0135));
        ((HBox) searchButton.getParent()).setSpacing(sceneWidth * 0.001);
        ((HBox) monitoringButton.getParent()).setSpacing(sceneWidth * 0.65);

        homeStage.setFullScreenExitHint("");
        homeStage.setFullScreen(true);
        homeStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
