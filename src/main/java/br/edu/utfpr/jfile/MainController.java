package br.edu.utfpr.jfile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    @FXML
    private StackPane mainPanel;

    @FXML
    private void actionManagerVideo(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("page-video.fxml"));
        mainPanel.getChildren().removeAll();
        mainPanel.getChildren().setAll(fxml);
    }

    @FXML
    private void actionManagerImagens(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("page-image.fxml"));
        mainPanel.getChildren().removeAll();
        mainPanel.getChildren().setAll(fxml);
    }
}