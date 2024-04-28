package com.github.zdanielm.sfm_projekt;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.zdanielm.sfm_projekt.Scenes.HomeScene;
import com.github.zdanielm.sfm_projekt.Scenes.LoginScene;
import com.github.zdanielm.sfm_projekt.Scenes.VillainsScene;
import com.github.zdanielm.sfm_projekt.controller.FXMLVillainsSceneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;
import org.hibernate.annotations.common.util.impl.Log;


public class MainApp extends Application {
    //FXMLVillainsSceneController controller;
    LoginScene loginScene = new LoginScene();
    Stage primaryStage = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage.setTitle("Batman Crime Database");
        loginScene.start(primaryStage);
        /*HomeScene homeScene = new HomeScene();
        homeScene.start(primaryStage);*/
        /*VillainsScene villainsScene = new VillainsScene();
        villainsScene.start(primaryStage);*/
    }

    public static void main(String[] args) {
        try {
            startDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        launch(args);
        stopDatabase();
    }

    private static Server s = new Server();

    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
}
