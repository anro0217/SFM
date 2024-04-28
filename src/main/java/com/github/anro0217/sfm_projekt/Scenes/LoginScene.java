package com.github.zdanielm.sfm_projekt.Scenes;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class LoginScene extends Application {
    private Stage loginStage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        this.loginStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXMLLoginScene.fxml"));
        root = loader.load();
        scene = new Scene(root, 1920, 1080);
        loginStage.setScene(scene);

        int res = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        double scale = (double)res/96;
        double sceneWidth = scene.getWidth()/scale;
        double sceneHeight = scene.getHeight()/scale;

        Button loginButton = (Button) loader.getNamespace().get("LoginButton");
        TextField userBox = (TextField) loader.getNamespace().get("UsernameField");
        PasswordField passBox = (PasswordField) loader.getNamespace().get("PasswordField");

        ((VBox) userBox.getParent()).setSpacing(sceneWidth * 0.008);
        ((VBox) userBox.getParent()).setPadding(new Insets(sceneHeight * 0.57, 0, 0, sceneWidth * 0.3965));
        userBox.setFont(Font.font(sceneWidth * 0.02));
        passBox.setFont(Font.font(sceneWidth * 0.02));
        userBox.setMaxWidth(sceneWidth * 0.17);
        passBox.setMaxWidth(sceneWidth * 0.17);

        loginButton.setPadding(new Insets(sceneHeight * 0.8, 0, 0, sceneWidth * 0.455));
        loginButton.setFont(Font.font(sceneWidth * 0.02));

        loginStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        root.getStyleClass().add("transparent-background");

        loginStage.setFullScreenExitHint("");
        loginStage.setFullScreen(true);
        loginStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
