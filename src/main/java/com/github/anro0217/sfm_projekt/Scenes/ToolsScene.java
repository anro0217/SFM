package com.github.zdanielm.sfm_projekt.Scenes;

import com.github.zdanielm.sfm_projekt.Repository.VillainsRepository;
import com.github.zdanielm.sfm_projekt.controller.FXMLVillainsSceneController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ToolsScene extends Application {
    private Stage villainsStage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        this.villainsStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXMLToolsScene.fxml"));
        root = loader.load();
        FXMLVillainsSceneController controller = loader.getController();

        scene = new Scene(root, 1920, 1080);
        villainsStage.setScene(scene);


        villainsStage.setFullScreenExitHint("");
        villainsStage.setFullScreen(true);
        villainsStage.show();
    }
    public static void main (String[]args){
        launch(args);
    }
}