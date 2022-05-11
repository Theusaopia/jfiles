package br.edu.utfpr.jfile;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class PageVideo {
    public void actionUploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Escolha um vídeo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de vídeo", "*.mp4"));

        File selectedFile = fileChooser.showOpenDialog(null);
    }
}
