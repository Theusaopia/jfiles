package br.edu.utfpr.jfile.util;

import javafx.scene.control.Alert;

public abstract class AlertMaker {
    public static Alert generateNewAlert(Alert.AlertType typeOfAlert, String contentText, String title) {
        Alert a = new Alert(typeOfAlert);
        a.setContentText(contentText);
        a.setTitle(title);

        return a;
    }
}
