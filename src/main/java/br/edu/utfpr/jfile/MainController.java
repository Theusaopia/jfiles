package br.edu.utfpr.jfile;

import br.edu.utfpr.jfile.util.KanyeRest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainController {
    @FXML
    private StackPane mainPanel;

    @FXML
    private Label labelKanyeRest;

    @FXML
    public void initialize() throws IOException {
        labelKanyeRest.setText(getKanyeRestQuote(makeRequestKanyeRest()) + "\n - Kanye West");
    }

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

    @FXML
    private void actionManagerAudio(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("page-audio.fxml"));
        mainPanel.getChildren().removeAll();
        mainPanel.getChildren().setAll(fxml);
    }

    //-------------------------UTIL Methods--------------------

    private String makeRequestKanyeRest() throws IOException {
        URL url = new URL("https://api.kanye.rest");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        return content.toString();
    }

    public String getKanyeRestQuote(String responseFromKanyeApi) throws JsonProcessingException {
        String stringResponse = responseFromKanyeApi;
        ObjectMapper mapper = new ObjectMapper();
        KanyeRest kanyeRestResponseObj = mapper.readValue(stringResponse, KanyeRest.class);

        return kanyeRestResponseObj.getQuote();
    }
}