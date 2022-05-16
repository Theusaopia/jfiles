package br.edu.utfpr.jfile;

import br.edu.utfpr.jfile.util.AlertMaker;
import br.edu.utfpr.jfile.util.AmazonService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PageImage {
    @FXML
    private ImageView imageContainer;

    @FXML
    private HBox hboxContainer;

    @FXML
    private Label imageInfo;

    @FXML
    private Button btnGoToDirectory;

    @FXML
    private Label amazonLink;

    File selectedFile = null;

    @FXML
    public void initialize() throws IOException {
       hboxContainer.setPrefWidth(834);
       hboxContainer.setPrefHeight(680);
       imageContainer.setImage(null);
       imageInfo.setText("Informação da imagem");
       btnGoToDirectory.setVisible(false);

       amazonLink.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               try {
                   URI uri = new URI(amazonLink.getText());
                   Desktop.getDesktop().browse(uri);
               } catch (URISyntaxException | IOException e) {
                   AlertMaker.generateNewAlert(Alert.AlertType.ERROR, "Houve um problema ao acessar o link", "Erro");
               }
           }
       });

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
            AlertMaker.generateNewAlert(Alert.AlertType.ERROR, "Imagem muito grande!!", "Error" ).show();
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

    public void actionSendToAmazon(ActionEvent event) {
        try {
           amazonLink.setText(AmazonService.uploadFile(selectedFile));
           AlertMaker.generateNewAlert(Alert.AlertType.INFORMATION, "Arquivo importado para a Amazon!", "INFO").show();
        }catch (Exception ex) {
            AlertMaker.generateNewAlert(Alert.AlertType.ERROR, "Erro ao importar para a amazon", "Erro").show();
        }
    }
}
