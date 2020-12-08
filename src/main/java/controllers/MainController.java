package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.statics.sounds.Sound;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public void btn_option(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/fxmls/Option.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("OPTION");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void btn_1play(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = new FXMLLoader().load(getClass().getResource("/main/java/fxmls/Game.fxml"));

        stage.hide();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
    public void btn_multiplay(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = new FXMLLoader().load(getClass().getResource("/main/java/fxmls/Multi.fxml"));
        stage.hide();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Sound.getInstance().getAudio(Sound.MENU).loop();
    }
//    public void btn_custom(ActionEvent event) throws IOException {
//        Stage stage = new Stage();
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Parent root = new FXMLLoader().load(getClass().getResource("/main/java/boomber/fxmls/Custom.fxml"));
//        stage.hide();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
}
