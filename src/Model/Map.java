package Model;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Map extends Group {
    public final static int TAILLE_CASE = 60;
    private final int NB_CASE_X = 10;
    private final int NB_CASE_Y = 10;

    public enum TypeCase {VIDE, CHEMIN, EMPLTOUR, TOUR}

    private final Image imageVide;
    private final Image imageChemin;
    private final Image imageEmplacementTour;
    private final Image imageTour;

    public TypeCase[][] grid;
    public ImageView[][] caseMap;

    String[][] tab;

    public Map() {
        String src = "../Ressources/image/";
        this.imageVide = new Image(Objects.requireNonNull(getClass().getResourceAsStream(src + "vide.png")));
        this.imageChemin = new Image(Objects.requireNonNull(getClass().getResourceAsStream(src + "chemin.png")));
        this.imageEmplacementTour = new Image(Objects.requireNonNull(getClass().getResourceAsStream(src + "emplacementTour.png")));
        this.imageTour = new Image(Objects.requireNonNull(getClass().getResourceAsStream(src + "tour.png")));

        this.grid = new TypeCase[this.NB_CASE_X][this.NB_CASE_Y];

        this.generateMap();

        Platform.runLater(this::creeMap);
    }

    private void generateMap() {
        File file = new File("src/Ressources/Map.txt");
        this.tab = new String[NB_CASE_X][NB_CASE_Y];

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            int compteur = 0;

            String line;
            while((line = br.readLine()) != null)
            {
                for (int i = 0; i < line.length(); i++) {
                    tab[i][compteur] = String.valueOf(line.charAt(i));
                }
                compteur++;
            }

            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initialiseMapGeneree();
    }

    public void creeMap() {
        if (this.caseMap == null) this.initialiseCaseMap();
        this.afficheMap();
    }

    private void initialiseMapGeneree() {
        for (int x=0; x<this.NB_CASE_X; x++) {
            for (int y=0; y<this.NB_CASE_Y; y++) {
                switch (this.tab[x][y]) {
                    case "V" -> this.grid[x][y] = TypeCase.VIDE;
                    case "E" -> this.grid[x][y] = TypeCase.EMPLTOUR;
                    case "C" -> this.grid[x][y] = TypeCase.CHEMIN;
                    case "T" -> this.grid[x][y] = TypeCase.TOUR;
                    default -> System.out.println("Erreur: La valeur de la case est inconnu");
                }
            }
        }
    }


    private void initialiseCaseMap() {
        this.caseMap = new ImageView[this.NB_CASE_X][this.NB_CASE_Y];
        for (int i = 0; i < this.NB_CASE_X; i++) {
            for (int j = 0; j < this.NB_CASE_Y; j++) {
                this.caseMap[i][j] = new ImageView();
                this.caseMap[i][j].setX((double) i * TAILLE_CASE);
                this.caseMap[i][j].setY((double) j * TAILLE_CASE);
                this.caseMap[i][j].setFitWidth(TAILLE_CASE);
                this.caseMap[i][j].setFitHeight(TAILLE_CASE);
                this.getChildren().add(this.caseMap[i][j]);
            }
        }
    }

    public void afficheMap() {
        for (int i = 0; i < this.NB_CASE_X; i++) {
            for (int j = 0; j < this.NB_CASE_Y; j++) {

                if (this.grid[i][j] == TypeCase.VIDE) {
                    this.caseMap[i][j].setImage(this.imageVide);
                } else if (this.grid[i][j] == TypeCase.CHEMIN) {
                    this.caseMap[i][j].setImage(this.imageChemin);
                } else if (this.grid[i][j] == TypeCase.EMPLTOUR) {
                    this.caseMap[i][j].setImage(this.imageEmplacementTour);
                } else if (this.grid[i][j] == TypeCase.TOUR) {
                    this.caseMap[i][j].setImage(this.imageTour);
                }
            }
        }
    }
}