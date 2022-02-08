package Controller;

import Model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    public static Stage primaryStage;

    Game game;

    Controller() {
        this.game = new Game();
    }

    @FXML
    public void switchToScene(ActionEvent event) throws IOException {
        Parent scoreView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/"+ ((Node) event.getSource()).getId() +".fxml")));
        if (primaryStage  == null) primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(scoreView));
        primaryStage.sizeToScene();
        primaryStage.show();
        scoreView.requestFocus();
    }
}
