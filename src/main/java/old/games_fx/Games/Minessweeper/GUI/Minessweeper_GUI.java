package old.games_fx.Games.Minessweeper.GUI;

import old.games_fx.Games.Minessweeper.GameObserver;
import old.games_fx.DefaultGrapics.ChangingLabel.ChangingLabel;
import old.games_fx.Games.Minessweeper.State;
import old.games_fx.Games.Minessweeper.Minesweeper;
import old.games_fx.DefaultGrapics.DefaultScene;
import old.games_fx.DefaultGrapics.IndexButton.Click;
import old.games_fx.DefaultGrapics.IndexButton.ClickObserver;
import old.games_fx.GameLogic.Queries.JOptionQueries;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * the scene of the game "Minesweeper"
 *
 * @author Elias Schorr, BBBaden
 * @since 29.09.2014
 * @version 1.0
 */
public class Minessweeper_GUI implements ClickObserver, GameObserver {

    private final Minesweeper game;
    private MinesField[][] fields;
    private final ChangingLabel time, score;
    private final Button newGame, reset;
    private final DefaultScene scene;

    /**
     *
     * @param stage
     */
    public Minessweeper_GUI(Stage stage) {
        this.game = new Minesweeper();
        scene = new DefaultScene(stage);
        newGame = new Button("new game");
        reset = new Button("reset");
        time = new ChangingLabel(null);
        score = new ChangingLabel(null);
        game.addScoreObserver(score);
        game.addTimeObserver(time);
        scene.addToMenuBar(newGame);
        scene.addToMenuBar(reset);
        scene.addToStatusBar(score.getLabel());
        scene.addToStatusBar(time.getLabel());
        game.addChangeObserver(this);
        newGame.setOnMouseClicked((Event event) -> {
            startNewGame();
        });
        reset.setOnMouseClicked((Event event) -> {
            game.reset();
        });
    }

    @Override
    public void onStateChange(Click clicked, int xKord, int yKord) {
        game.onStateChange(clicked, xKord, yKord);
    }

    /**
     * starts an new game
     */
    public void startNewGame() {
        JOptionQueries jp = new JOptionQueries();
        int length = jp.askSize("length", 10, 100, "Minessweeper");
        int height = jp.askSize("height", 10, 100, "Minessweeper");
        int bombs = jp.askSize("bombs", length * height / 10, length * height / 4, "Minessweeper");
        game.newGame(height, length, bombs);
    }

    @Override
    public void newGame(int heigth, int length) {
        fields = new MinesField[length][heigth];//new fields
        GridPane g = new GridPane();//gridpane
        for (int i = 0; i < length; i++) {
            for (int index = 0; index < heigth; index++) {
                fields[i][index] = new MinesField(i, index);
                fields[i][index].addChangeObserver(this);
                g.add(fields[i][index].getButton(), i, index);
            }
        }
        g.autosize();
        scene.setCenter(g);
    }

    /**
     *
     * @param x
     * @param y
     * @param state
     */
    public void setField(int x, int y, State state) {
        new MinesField(x, y).addChangeObserver(this);
    }

    /**
     *
     * @return
     */
    public ChangingLabel getTimeLabel() {
        return time;
    }

    /**
     *
     * @return
     */
    public ChangingLabel getScoreabel() {
        return score;
    }

    @Override
    public void onStateChange(State state, int xKord, int yKord) {
        fields[xKord][yKord].setResult(state);
    }

    /**
     *
     */
    @Override
    public void onGameOver() {
        JOptionQueries jp = new JOptionQueries();
        jp.showMessage("The game is over!!!", "Minessweeper");
    }

    /**
     *
     */
    @Override
    public void onGameWon() {
        JOptionQueries jp = new JOptionQueries();
        jp.showMessage("Congratiolations, you have won.", "Minessweeper");
    }
}
