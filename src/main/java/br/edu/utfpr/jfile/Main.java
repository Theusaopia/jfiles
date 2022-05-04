package br.edu.utfpr.jfile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1150, 771);
        stage.setTitle("JFile - Arquivos");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.getIcons().add(new Image("file:src/main/resources/br/edu/utfpr/imgs/icone_pasta_30.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}