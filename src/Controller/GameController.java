package Controller;

import Model.Game;
import Model.Map;
import Model.Tower;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class GameController extends Controller {
    @FXML Label gold;
    @FXML Label round;
    @FXML public Map map;

    public GameController() {
        super();
    }

    @FXML
    void initialize() {
        gold.setText(game.gold + "");
        round.setText(game.round + "");
    }

    @FXML
    public void buyTower(ActionEvent event) {
        Tower tower = new Tower();
        if (game.gold >=tower.price) {
            game.gold -= tower.price;

        initialize();
        }
    }

    public void wave(ActionEvent event) {
        game.round += 1;
    }
}
