package com.github.zdanielm.sfm_projekt.controller;

import com.github.zdanielm.sfm_projekt.Repository.VillainsRepository;
import com.github.zdanielm.sfm_projekt.Scenes.HomeScene;
import com.github.zdanielm.sfm_projekt.Scenes.VillainsScene;
import com.github.zdanielm.sfm_projekt.model.Villains;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLVillainsSceneController implements Initializable {
    @FXML
    private TextField SearchBox;
    @FXML
    private ListView<String> SearchResults;
    @FXML
    private TextField VillainName;
    @FXML
    private TextArea VillainDescription;
    @FXML
    private TextArea WeaknessList;
    @FXML
    private ListView<String> CommittedCrimes;
    @FXML
    private ProgressBar ProgressBar1;
    @FXML
    private ProgressBar ProgressBar2;
    @FXML
    private ProgressBar ProgressBar3;
    @FXML
    private ProgressBar ProgressBar4;
    @FXML
    private ProgressBar ProgressBar5;
    @FXML
    private Button BackButton;
    @FXML
    private ImageView FingerprintImage;
    private VillainsRepository villainsRepository;
    String selectedVillainNickname;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        villainsRepository = new VillainsRepository();

        SearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSearchResults(newValue);
        });

        SearchResults.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                selectedVillainNickname = SearchResults.getSelectionModel().getSelectedItem();
                loadVillainData(selectedVillainNickname);
            }
        });
    }
    public void processNumber(int receivedNumber) {
        if(receivedNumber >= 0){
            if(receivedNumber < 17) {
                SearchResults.requestFocus();
                SearchResults.getSelectionModel().select(receivedNumber);
                SearchResults.getFocusModel().focus(receivedNumber);
                selectedVillainNickname = SearchResults.getItems().get(receivedNumber);
                loadVillainData(selectedVillainNickname);
            }
            if(receivedNumber == 17){
                SearchBox.requestFocus();
            }
        }
    }
    private void loadVillainData(String nickname) {
        Villains villain = villainsRepository.findNickname(nickname);

        VillainName.setText(villain.getNickname());
        VillainDescription.setText(villain.getDescription());
        WeaknessList.setText(villain.getWeakness());
        CommittedCrimes.getItems().clear();
        CommittedCrimes.getItems().addAll(villainsRepository.findCrimesByVillain(nickname));
        switchOnBars(villain.getDanger_level());
        //FingerprintImage = new ImageView(new Image("/pictures/fingerprints/FPrint" + pairFingerprints(nickname) + ".png"));
        //FingerprintImage.setImage(new Image("/pictures/fingerprints/FPrint" + pairFingerprints(nickname) + ".png"));
        //FingerprintImage.setId("FingerprintImage");
    }
    private void updateSearchResults(String searchText) {
        SearchResults.getItems().clear();
        SearchResults.getItems().addAll(villainsRepository.searchNicknames(searchText.toLowerCase()));
    }
    public int pairFingerprints(String nickname){
        return villainsRepository.findNames().indexOf(nickname) + 1;
    }
    @FXML
    private void buttonClicked() {
        openHomeScene();
    }

    private void openHomeScene() {
        HomeScene homeScene = new HomeScene();
        try {
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
            homeScene.start((Stage) BackButton.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchOnBars(int level) {
        switch (level) {
            case 1:
                ProgressBar1.setProgress(1);
                ProgressBar2.setProgress(0);
                ProgressBar3.setProgress(0);
                ProgressBar4.setProgress(0);
                ProgressBar5.setProgress(0);
                break;
            case 2:
                ProgressBar1.setProgress(1);
                ProgressBar2.setProgress(1);
                ProgressBar3.setProgress(0);
                ProgressBar4.setProgress(0);
                ProgressBar5.setProgress(0);
                break;
            case 3:
                ProgressBar1.setProgress(1);
                ProgressBar2.setProgress(1);
                ProgressBar3.setProgress(1);
                ProgressBar4.setProgress(0);
                ProgressBar5.setProgress(0);
                break;
            case 4:
                ProgressBar1.setProgress(1);
                ProgressBar2.setProgress(1);
                ProgressBar3.setProgress(1);
                ProgressBar4.setProgress(1);
                ProgressBar5.setProgress(0);
                break;
            case 5:
                ProgressBar1.setProgress(1);
                ProgressBar2.setProgress(1);
                ProgressBar3.setProgress(1);
                ProgressBar4.setProgress(1);
                ProgressBar5.setProgress(1);
                break;
            default:
                break;
        }
    }
}