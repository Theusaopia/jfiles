package br.edu.utfpr.jfile;

import br.edu.utfpr.jfile.util.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PageImage {
    @FXML
    private ImageView imageContainer;

    @FXML
    private HBox hboxContainer;

    @FXML
    private Label imageInfo;

    @FXML
    private Button btnGoToDirectory;

    File selectedFile = null;

    @FXML
    public void initialize() throws IOException {
       hboxContainer.setPrefWidth(834);
       hboxContainer.setPrefHeight(680);
       imageContainer.setImage(null);
       imageInfo.setText("Informação da imagem");
       btnGoToDirectory.setVisible(false);

       if(selectedFile != null) {
           selectedFile = null;
       }
    }

    public void actionUploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Escolha uma imagem");
        fileChooser.setInitialDirectory(new File("C:\\temp\\fotos"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de imagem", "*.png", "*.jpg"));

        selectedFile = fileChooser.showOpenDialog(null);
        Image image= new Image(selectedFile.getAbsolutePath());

        if(validateImageSize(image))
            return;

        setNewImageToScreen(image);
        setImageInfo(image, selectedFile);
    }


    public boolean validateImageSize(Image image) {
        if(image.getWidth() > 834 || image.getHeight() > 680) {
            AlertBuilder.generateNewAlert(Alert.AlertType.ERROR, "Imagem muito grande!!", "Error" ).show();
            return true;
        }
        return false;
    }

    public void setNewImageToScreen(Image image) {
        hboxContainer.setPrefWidth(image.getWidth());
        hboxContainer.setPrefHeight(image.getHeight());

        imageContainer.setImage(image);
    }

    private void setImageInfo(Image image, File selectedFile) {
        StringBuilder builder = new StringBuilder();

        builder.append("Nome: " + selectedFile.getName() + " | ");
        builder.append("Tamanho: " + (int) image.getWidth() + " X " + (int) image.getHeight());

        imageInfo.setText(builder.toString());
        btnGoToDirectory.setVisible(true);
    }

    public void actionGoToDirectory(ActionEvent event) {
        try{
            if(selectedFile != null) {
                Desktop d = Desktop.getDesktop();
                String t = selectedFile.getParentFile().toString();
                d.open(selectedFile.getParentFile());
            }
        }catch(IOException ex) {

        }
    }
}
