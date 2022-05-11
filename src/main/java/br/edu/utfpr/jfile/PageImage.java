package br.edu.utfpr.jfile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;

public class PageImage {

    public void actionUploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Escolha uma imagem");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de imagem", "*.png", "*.jpg"));

        File selectedFile = fileChooser.showOpenDialog(null);
    }
}
