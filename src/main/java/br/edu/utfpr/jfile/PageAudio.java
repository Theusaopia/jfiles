package br.edu.utfpr.jfile;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class PageAudio implements Initializable {

    @FXML
    private Label songName;

    @FXML
    private ProgressBar progressSong;

    @FXML
    private Slider sliderVolume;

    @FXML
    private ComboBox<String> speed;

    private File selectedFile;

    private Media media;
    private MediaPlayer mediaPlayer;

    private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200};

    private Timer timer;
    private TimerTask task;
    private boolean running;

    public void actionUploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Escolha um aúdio");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de aúdio", "*.mp3"));


        selectedFile = fileChooser.showOpenDialog(null);

        media = new Media(selectedFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        songName.setText(selectedFile.getName());

        sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(sliderVolume.getValue() * 0.01);
            }

        });

    }

    public void actionPlay(ActionEvent actionEvent) {
        beginTimer();
        changeSpeed(null);
        mediaPlayer.play();
    }

    public void actionReset(ActionEvent actionEvent) {
        progressSong.setProgress(0);
        mediaPlayer.seek(Duration.seconds(0.0));
    }

    public void actionPause(ActionEvent actionEvent) {
        cancelTimer();
        mediaPlayer.pause();
    }


    public void changeSpeed(ActionEvent event){
        if(speed.getValue() == null) {
            mediaPlayer.setRate(1);
        }
        else {
            //mediaPlayer.setRate(Integer.parseInt(speedBox.getValue()) * 0.01);
            mediaPlayer.setRate(Integer.parseInt(speed.getValue().substring(0, speed.getValue().length() - 1)) * 0.01);
        }
    }

    public void cancelTimer() {
        running = false;
        timer.cancel();
    }

    public void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {

                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                progressSong.setProgress(current/end);

                if(current/end == 1) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 0; i < speeds.length; i++) {

            speed.getItems().add(Integer.toString(speeds[i])+"%");
        }
        speed.setOnAction(this::changeSpeed);
        progressSong.setStyle("-fx-accent: #00FF00;");
    }


}
