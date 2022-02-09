package Controller;

import Model.Game;
import Model.Map;
import Model.Tower;
import Model.Wave;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.util.Arrays;
import java.util.Objects;


public class GameController extends Controller {
    @FXML Label gold;
    @FXML Label round;
    @FXML public Map map;
    ImageView towerDragged;

    public GameController() {
        super();
        Platform.runLater(() -> {
            map.creeMap();
            for (ImageView[] i : map.caseMap) {
                for (ImageView j : i) {
                    j.setOnDragDetected(this::dragDetected);
                    j.setOnDragOver(this::dragOver);
                    j.setOnDragDone(this::dragDone);
                    j.setOnDragDropped(this::dragDropped);
                }
            }
        });
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
        Wave wave = new Wave(game.round);

        initialize();
    }


    @FXML
    public void dragDetected(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        if (source.getId() != null && source.getId().contains("shop_")) {
            Dragboard db = source.startDragAndDrop(TransferMode.COPY);

            ClipboardContent content = new ClipboardContent();
            content.putImage(source.getImage());
            db.setContent(content);
            towerDragged = source;
        }

        event.consume();

    }

    @FXML
    public void dragDone(DragEvent event) {

    }

    @FXML
    public void dragOver(DragEvent event) {
        final Dragboard dragboard = event.getDragboard();
        if (Objects.equals(((ImageView) event.getSource()).getId(), "emplacementTour")) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    public void dragDropped(DragEvent event) {

    }
}
