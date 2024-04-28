package com.github.zdanielm.sfm_projekt.controller;

import com.github.zdanielm.sfm_projekt.Scenes.LoginScene;
import com.github.zdanielm.sfm_projekt.Scenes.ToolsScene;
import com.github.zdanielm.sfm_projekt.Scenes.VillainsScene;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLHomeSceneController implements Initializable {

    @FXML
    private Button LoginButton;
    @FXML
    private Button SearchButton;
    @FXML
    private Button MonitoringButton;
    @FXML
    private Button QuitButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void searchButtonClicked() {
        openVillainsScene();
    }
    @FXML
    private void loginButtonClicked(){
        openLoginScene();
    }
    @FXML
    private void monitoringButtonClicked(){
       /* ToolsScene toolsScene = new ToolsScene();
        try {
            toolsScene.start((Stage) MonitoringButton.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*ToolsScene toolsScene = new ToolsScene();
        try {
            toolsScene.start((Stage) MonitoringButton.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    @FXML
    private void quitButtonClicked(){
        Stage stage = (Stage) QuitButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    private void openVillainsScene() {
        VillainsScene villainsScene = new VillainsScene();
        try {
            villainsScene.start((Stage) SearchButton.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void openLoginScene() {
        LoginScene loginScene = new LoginScene();
        try {
            loginScene.start((Stage) LoginButton.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
