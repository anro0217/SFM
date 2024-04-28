package com.github.zdanielm.sfm_projekt.controller;

import com.github.zdanielm.sfm_projekt.Scenes.HomeScene;
import com.github.zdanielm.sfm_projekt.Scenes.VillainsScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLLoginSceneController implements Initializable {

    @FXML
    private TextField UsernameField;

    @FXML
    private PasswordField PasswordField;
    @FXML
    private Button LoginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PasswordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER) && handleLoginAttempt()) {
                openHomeScene();
            } else {

            }
        });
    }
        @FXML
        private void buttonClicked() {
            if (handleLoginAttempt()) {
                openHomeScene();
            } else {

            }
        }
        private void openHomeScene() {
            HomeScene homeScene = new HomeScene();
            try {
                homeScene.start((Stage) LoginButton.getScene().getWindow());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        private boolean handleLoginAttempt() {
            String username = UsernameField.getText();
            String password = PasswordField.getText();

            return username.equals("admin") && password.equals("password");
        }

    }
