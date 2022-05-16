package br.edu.utfpr.jfile;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PageVideo implements Initializable {
    @FXML
    MediaView videoPlayer;

    @FXML
    Slider volumeSlider;

    File selectedFile = null;

    MediaPlayer mediaPlayer;

    int volume;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
    }

    public void actionUploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Escolha um vídeo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de vídeo", "*.mp4"));

        selectedFile = fileChooser.showOpenDialog(null);

        Media media = new Media(selectedFile.toURI().toString());

        mediaPlayer = new MediaPlayer(media);

        videoPlayer.setMediaPlayer(mediaPlayer);
    }

    public void actionPlayVideo(ActionEvent event) {
        mediaPlayer.play();

    }

    public void actionPauseVideo(ActionEvent event) {
        mediaPlayer.pause();
    }

    public void actionResetVideo(ActionEvent event) {
        mediaPlayer.seek(Duration.seconds(0.0));
    }
}
