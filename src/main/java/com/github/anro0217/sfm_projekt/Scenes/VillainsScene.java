package com.github.zdanielm.sfm_projekt.Scenes;

import com.github.zdanielm.sfm_projekt.Repository.VillainsRepository;
import com.github.zdanielm.sfm_projekt.controller.FXMLVillainsSceneController;
import com.github.zdanielm.sfm_projekt.model.Villains;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VillainsScene extends Application {
    private Stage villainsStage;
    private Scene scene;
    private Parent root;
    private double scale;
    private VillainsRepository villainsRepository;
    @FXML
    private ImageView FingerprintImage;
    @Override
    public void start(Stage stage) throws Exception {
        this.villainsStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXMLVillainsScene.fxml"));
        root = loader.load();
        FXMLVillainsSceneController controller = loader.getController();

        scene = new Scene(root, 1920, 1080);
        villainsStage.setScene(scene);

        int res = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        scale = (double) res / 96;
        double sceneWidth = scene.getWidth() / scale;
        double sceneHeight = scene.getHeight() / scale;

        TextField searchBox = (TextField) loader.getNamespace().get("SearchBox");
        ListView searchResults = (ListView) loader.getNamespace().get("SearchResults");

        TextField villainName = (TextField) loader.getNamespace().get("VillainName");
        TextArea villainDescription = (TextArea) loader.getNamespace().get("VillainDescription");
        Button backButton = (Button) loader.getNamespace().get("BackButton");
        //ImageView villainImage = (ImageView) loader.getNamespace().get("VillainImage");

        TextArea weaknessList = (TextArea) loader.getNamespace().get("WeaknessList");
        Label currentTime = (Label) loader.getNamespace().get("CurrentTime");
        ListView committedCrimes = (ListView) loader.getNamespace().get("CommittedCrimes");
        ImageView fingerprintImage = (ImageView) loader.getNamespace().get("FingerprintImage");

        ProgressBar progressBar1 = (ProgressBar) loader.getNamespace().get("ProgressBar1");
        ProgressBar progressBar2 = (ProgressBar) loader.getNamespace().get("ProgressBar2");
        ProgressBar progressBar3 = (ProgressBar) loader.getNamespace().get("ProgressBar3");
        ProgressBar progressBar4 = (ProgressBar) loader.getNamespace().get("ProgressBar4");
        ProgressBar progressBar5 = (ProgressBar) loader.getNamespace().get("ProgressBar5");

        searchBox.setFont(Font.font(sceneWidth * 0.014));
        searchBox.setStyle("-fx-text-fill: #cccccc;");
        searchResults.setStyle("-fx-font-size: " + sceneWidth * 0.014 + "px;");
        searchResults.getStyleClass().add("special-list-view");
        ((VBox) searchBox.getParent()).setSpacing(sceneHeight * 0.0135);
        ((VBox) searchBox.getParent()).setPadding(new Insets(sceneHeight * 0.046, 0, 0, sceneWidth * 0.011));
        searchBox.setPrefWidth(sceneWidth * 0.3125);
        searchResults.setPrefWidth(sceneWidth * 0.3125);
        searchResults.setPrefHeight(sceneHeight * 0.8);

        villainName.setFont(Font.font(sceneWidth * 0.025));
        villainDescription.setFont(Font.font(sceneWidth * 0.01));
        villainDescription.setWrapText(true);
        ((VBox) villainName.getParent()).setPadding(new Insets(sceneHeight * 0.148, 0, 0, sceneWidth * 0.34));
        ((VBox) villainDescription.getParent()).setPadding(new Insets(sceneHeight * 0.731, 0, 0, sceneWidth * 0.373));
        villainDescription.setPrefWidth(sceneWidth * 0.16);
        villainDescription.setPrefHeight(sceneHeight * 0.24);

        weaknessList.setFont(Font.font(sceneWidth * 0.01));
        weaknessList.setWrapText(true);
        ((VBox) weaknessList.getParent()).setPadding(new Insets(sceneHeight * 0.7278, 0, 0, sceneWidth * 0.752));
        weaknessList.setPrefWidth(sceneWidth * 0.109);
        weaknessList.setPrefHeight(sceneHeight * 0.26);

        currentTime.setFont(Font.font(sceneWidth * 0.03));
        currentTime.setPadding(new Insets(sceneHeight * 0.173, 0, 0, sceneWidth * 0.764));
        backButton.getStyleClass().add("button");
        backButton.setFont(Font.font(sceneWidth * 0.02));
        ((HBox) backButton.getParent()).setPadding(new Insets(sceneHeight* 0.92, 0, 0, sceneWidth * 0.9));

        committedCrimes.setStyle("-fx-font-size: " + sceneWidth * 0.008 + "px;");
        ((VBox) committedCrimes.getParent()).setPadding(new Insets(sceneHeight * 0.1546, 0, 0, sceneWidth * 0.8948));
        committedCrimes.setPrefWidth(sceneWidth * 0.0912);
        committedCrimes.setPrefHeight(sceneHeight * 0.292);

        ((HBox) progressBar1.getParent()).setPadding(new Insets(sceneHeight * 0.722, 0, 0, sceneWidth * 0.55));
        progressBar1.setPrefWidth(sceneWidth * 0.0182);
        progressBar1.setPrefHeight(sceneHeight * 0.0278);
        progressBar2.setPrefWidth(sceneWidth * 0.0182);
        progressBar2.setPrefHeight(sceneHeight * 0.0278);
        progressBar3.setPrefWidth(sceneWidth * 0.0182);
        progressBar3.setPrefHeight(sceneHeight * 0.0278);
        progressBar4.setPrefWidth(sceneWidth * 0.0182);
        progressBar4.setPrefHeight(sceneHeight * 0.0278);
        progressBar5.setPrefWidth(sceneWidth * 0.0182);;
        progressBar5.setPrefHeight(sceneHeight * 0.0278);

        ((VBox) fingerprintImage.getParent()).setPadding(new Insets(sceneHeight * 0.52, 0, 0,sceneWidth * 0.894));
        fingerprintImage.setFitHeight(sceneHeight * 0.1111);
        fingerprintImage.setFitWidth(sceneWidth * 0.0885);


        searchBox.requestFocus();
        committedCrimes.requestFocus();
        backButton.requestFocus();
        searchResults.requestFocus();
        villainName.setEditable(false);
        villainDescription.setEditable(false);
        villainName.setFocusTraversable(false);
        villainDescription.setFocusTraversable(false);
        weaknessList.setEditable(false);
        weaknessList.setFocusTraversable(false);
        backButton.setFocusTraversable(false);
        currentTime.setFocusTraversable(false);
        committedCrimes.setFocusTraversable(false);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> updateLabel(currentTime)),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            double x = event.getSceneX() * scale;
            double y = event.getSceneY() * scale;
            int clickIndex = determineClickedCell(x, y);
            sendNumberToReceiver(clickIndex,controller);
            clickIndex++;

            VBox vBox = (VBox) root.lookup("#vBox");

            ImageView existingImageView = (ImageView) root.lookup("#FingerprintImage");
            if (existingImageView != null) {
                vBox.getChildren().remove(existingImageView);
            }

            FingerprintImage = new ImageView(new Image("/pictures/fingerprints/FPrint" + clickIndex  + ".png"));
            FingerprintImage.setId("FingerprintImage");
            vBox.getChildren().add(FingerprintImage);
        });

        villainsStage.setFullScreenExitHint("");
        villainsStage.setFullScreen(true);
        villainsStage.show();
    }
    public void sendNumberToReceiver(int numberToSend, FXMLVillainsSceneController receiver) {
        receiver.processNumber(numberToSend);
    }
    public Integer determineClickedCell(double clickX, double clickY) {
        double startXl = 16.0, startYl = 120.0, endXl = 626.0, endYl = 982.0; //lista
        double startXs = 16.0, startYs = 48.0, endXs = 626.0, endYs = 110.0;  //search

        if (clickX >= startXl && clickX <= endXl && clickY >= startYl && clickY <= endYl) {
            return (int) ((clickY - startYl) / 53);
        } else if(clickX >= startXs && clickX <= endXs && clickY >= startYs && clickY <= endYs){
            return 17;
        } else{
            return -1;
        }
    }
    public int pairFingerprints(String nickname){
        return villainsRepository.findNames().indexOf(nickname) + 1;
    }
    private void updateLabel(Label label) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        label.setText(formattedTime);
    }
        public static void main (String[]args){
            launch(args);
        }
    }