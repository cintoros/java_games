package old.games_swing.Games.g2048;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.GameLogic.ScoreCounter;
import old.games_swing.Games.g2048.autosolver.AutoSolver;
import old.games_swing.Games.g2048.autosolver.GameRules;
import old.games_swing.Games.g2048.g2048.Direction;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * this is the class that is responsible for the Gui of the game 2048
 *
 * @author Elias
 */
public class g2048Gui implements GameObserver {

    //Variablendefinition
    private final DefFrame frame;
    private final JPanel background;
    private final JButton tipp, newGame, auto, nextStep;
    private final JButton[][] grid;
    private final ScoreCounter points;
    private final ImageIcon[] tiles = {
        new ImageIcon(this.getClass().getResource("./images/1.png")),
        new ImageIcon(this.getClass().getResource("./images/2.png")),
        new ImageIcon(this.getClass().getResource("./images/4.png")),
        new ImageIcon(this.getClass().getResource("./images/8.png")),
        new ImageIcon(this.getClass().getResource("./images/16.png")),
        new ImageIcon(this.getClass().getResource("./images/32.png")),
        new ImageIcon(this.getClass().getResource("./images/64.png")),
        new ImageIcon(this.getClass().getResource("./images/128.png")),
        new ImageIcon(this.getClass().getResource("./images/256.png")),
        new ImageIcon(this.getClass().getResource("./images/512.png")),
        new ImageIcon(this.getClass().getResource("./images/1024.png")),
        new ImageIcon(this.getClass().getResource("./images/2048.png"))
    };
    private final g2048 game;
    private final GameRules nextGameStep;
    private AutoSolver autosolver;

    /**
     * creates an new Gui for the gaame 2048 with all required variables
     *
     * @param fensterImport
     */
    public g2048Gui(DefFrame fensterImport) {
        points = new ScoreCounter("Punkte: ", 0);
        this.frame = fensterImport;
        this.background = new JPanel(new GridLayout(4, 4));
        this.grid = new JButton[4][4];
        g2048 mainGame = new g2048();
        nextGameStep = new GameRules();
        this.game = mainGame;
        this.game.addGameObserver(this);
        frame.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (!game.isOver()) {
                    pressArrows(evt.getKeyCode());
                }
            }
        });
        background.setBackground(new Color(187, 173, 160));
        for (JButton[] row : grid) {
            for (int j = 0; j < grid.length; j++) {
                row[j] = new JButton();
                row[j].setEnabled(false);
                background.add(row[j]);
            }
        }
        frame.add(background);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        tipp = new JButton("tipp");
        tipp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if (!game.isOver()) {
                    getClue();
                }
                frame.requestFocus();
            }
        });
        frame.addToMenu(tipp);
        newGame = new JButton("neu");
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                reset();
            }
        });
        frame.addToMenu(newGame);
        auto = new JButton("Auto");
        auto.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if (!game.isOver()) {
                    auto();
                }
                frame.requestFocus();
            }
        });
        frame.addToMenu(auto);
        nextStep = new JButton("next Step");
        nextStep.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if (!game.isOver()) {
                    nextStep();
                }
            }
        });
        frame.addToMenu(nextStep);
        frame.pack();
        frame.addToMenu(points.getchangingLabel());
        autosolver = new AutoSolver(this, nextGameStep, game, 500);
        autosolver.pleaseStop();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {//wenn das Fenster geschlossen wird sollen alle aktiven treads beendet werden.
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                autosolver.pleaseStop();
            }
        });
        game.reset();
    }

    private void restartThread() {//restarts the Tread 
        int eingabe = JOptionPane.showConfirmDialog(null, "Soll der Autosolver die einzelnen Schritte anzeigen?(oder nur das Resultat)", "Start", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (eingabe == JOptionPane.YES_OPTION) {
            autosolver = new AutoSolver(this, nextGameStep, game, 500);
        } else {
            autosolver = new AutoSolver(this, nextGameStep, game, 0);
        }
    }

    /**
     * updates the Gui with the newest inforamtion of the game
     *
     * @param state
     */
    public void updateBoard(int[][] state) {
        int[][] b = state;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int icon = 0;
                if (b[i][j] > 0) {
                    icon = (int) (Math.log(b[i][j]) / Math.log(2));
                }
                grid[i][j].setIcon(tiles[icon]);
                grid[i][j].setDisabledIcon(tiles[icon]);
            }
        }
        this.points.setScore(1, game.getScore());
        frame.requestFocus();
    }

    private void pressArrows(int e) {//action when an arrow key is hit
        Direction direction = null;
        switch (e) {
            case 37:
                direction = Direction.LEFT;
                break;
            case 38:
                direction = Direction.UP;
                break;
            case 39:
                direction = Direction.RIGHT;
                break;
            case 40:
                direction = Direction.DOWN;
                break;
        }
        if (direction != null) {
            autosolver.pleaseStop();
            game.move(direction);
        }
    }

    private void getClue() {//action if the user wants an hint
        autosolver.pleaseStop();
        String message = "";
        switch (nextGameStep.simulate(game.getState())) {
            case LEFT:
                message = "Der nächste Schritt ist nach links";
                break;
            case RIGHT:
                message = "Der nächste Schritt ist nach rechts";
                break;
            case UP:
                message = "Der nächste Schritt ist nach oben";
                break;
            case DOWN:
                message = "Der nächste Schritt ist nach unten";
                break;
        }
        JOptionPane.showMessageDialog(null, message, "2048", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reset() {//resets the game
        autosolver.pleaseStop();
        game.reset();
    }

    private void auto() {//automatically palys the game until an other input is done
        if (autosolver.isCalculating()) {
            autosolver.pleaseStop();
        } else {
            restartThread();
            autosolver.start();
        }
    }

    private void nextStep() {//makes the next logical step
        autosolver.pleaseStop();
        Direction suggestedStep = nextGameStep.simulate(game.getState());
        game.move(suggestedStep);
    }

    /**
     * makes the move
     *
     * @param a the Direction of the move
     */
    public void nextMove(Direction a) {
        game.move(a);
    }

    @Override
    public void onStateChange(int[][] state) {//the board is updated whenever the state of the game changes
        this.updateBoard(state);
    }
}
