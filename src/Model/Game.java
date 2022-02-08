package Model;

import java.util.ArrayList;

public class Game {
    public int round;
    public int gold;
    public Tower[] towers;

    public Game() {
        this.towers = new Tower[5];
        this.round = 0;
        this.gold = 500;
    }

}
