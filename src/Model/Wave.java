package Model;

public class Wave {
    Ennemy[] ennemies;
    boolean started;
    int round;

    public Wave(int round) {
        this.round = round;

        initialize();
    }

    private void initialize() {
        switch (round) {
            case 1 -> ennemies = new Ennemy[]{new Ennemy()};
            default -> ennemies = new Ennemy[]{};
        }
        start();
    }

    private void start() {
        started = true;
    }
}
