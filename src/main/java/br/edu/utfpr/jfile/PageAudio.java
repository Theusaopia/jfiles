package br.edu.utfpr.jfile;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class PageAudio {
    public void actionUploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Escolha um aúdio");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de aúdio", "*.mp3"));

        File selectedFile = fileChooser.showOpenDialog(null);
    }
}
