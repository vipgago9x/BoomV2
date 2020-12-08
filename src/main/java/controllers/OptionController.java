package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionController {
    @FXML
    private AnchorPane Option;

    @FXML
    public void close_option(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()) .getScene().getWindow();
        stage.hide();
    }
}

