package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController extends Controller {

    @FXML
    public void leave() {
        Platform.exit();
        System.exit(0);
    }
}
