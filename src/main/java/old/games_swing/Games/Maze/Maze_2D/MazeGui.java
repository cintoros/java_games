package old.games_swing.Games.Maze.Maze_2D;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.Games.Maze.MazeGame;
import old.games_swing.KeybordListener.KeyboardListener;
import old.games_swing.KeybordListener.KeyObserver;
import old.games_swing.Sounds.Sounds;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * the Gui of the Maze Game
 *
 * @author Elias
 */
public class MazeGui implements KeyObserver {

    private final Sounds sound;//Classes
    private final DefFrame frame;
    private final MazeGame game;
    private MazeObject fields[][];//Gui Objects
    private JPanel backGround;
    private final JButton newGame, resetGame;
    private boolean ende, gestartet;//variables
    private final int oldPosition[];
    private int height, lenght;
    private final KeyboardListener listener;

    /**
     * constructor a new MazeGui with all objects and Actions is created
     *
     * @param parent
     */
    public MazeGui(DefFrame parent) {
        this.listener = new KeyboardListener();
        listener.addKeyObserver(this);
        parent.addKeyListener(listener);
        this.game = new MazeGame();
        this.oldPosition = new int[2];
        sound = new Sounds();
        frame = parent;
        newGame = new JButton("neues Spiel");
        resetGame = new JButton("Reset");
        parent.addToMenu(newGame);
        parent.addToMenu(resetGame);
        initListener();
        frame.pack();
        gestartet = false;
        ende = false;
        frame.requestFocus();
        backGround = new JPanel();
        frame.add(backGround);
    }

    private void initListener() {
        newGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {//actions when the player wants an new Maze
                while (true) {
                    try {
                        sound.hello();
                        String eingabe = JOptionPane.showInputDialog(null, "Wie hoch soll das Labyrinth sein (mindestens 10 max 100)", "Maze", JOptionPane.QUESTION_MESSAGE);
                        height = Integer.parseInt(eingabe);
                        if (height >= 10 && height <= 1000) {
                            break;
                        } else {
                            sound.lose();
                            JOptionPane.showMessageDialog(null, "Bitte geben Sie eine Zahl zwischen 10 und 100 ein", "Maze", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (HeadlessException | NumberFormatException ex) {
                        sound.lose();
                        JOptionPane.showMessageDialog(null, "Bitte tätigen Sie eine gültige Eingabe", "Maze", JOptionPane.WARNING_MESSAGE);
                    }
                }
                while (true) {
                    try {
                        sound.hello();
                        String eingabe = JOptionPane.showInputDialog(null, "Wie breit soll das Labyrinth sein (mindestens 10 max 100)", "Maze", JOptionPane.QUESTION_MESSAGE);
                        lenght = Integer.parseInt(eingabe);
                        if (lenght >= 10 && lenght <= 1000) {
                            break;
                        } else {
                            sound.lose();
                            JOptionPane.showMessageDialog(null, "Bitte geben Sie eine Zahl zwischen 10 und 100 ein", "Maze", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (HeadlessException | NumberFormatException ex) {
                        sound.lose();
                        JOptionPane.showMessageDialog(null, "Bitte tätigen Sie eine gültige Eingabe", "Maze", JOptionPane.WARNING_MESSAGE);
                    }
                }
                game.newGame(height, lenght);
                resetBoard();
                //this line ist for the correct displlaing of the object when it has the same size as before
                frame.setSize(frame.getSize().width + 1, frame.getSize().height + 1);
            }
        });
        resetGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (gestartet) {
                    reset();
                }
            }
        });
    }

    /**
     * resets the player position
     */
    public void reset() {
        game.reset();
        ende = false;
        updateBoard();
    }

    /**
     * updates the board with the newest position of the player
     */
    public void updateBoard() {
        try {
            fields[oldPosition[1]][oldPosition[0]].setBackground(Color.white);
        } catch (Exception ex) {
        }
        int[] a = game.getPlayerPosition();
        fields[a[1]][a[0]].setBackground(Color.green);
        try {
            if (a[1] != oldPosition[1] || a[0] != oldPosition[0]) {
                fields[oldPosition[1]][oldPosition[0]].goOver();
            }
        } catch (Exception ex) {
        }
        oldPosition[0] = a[0];
        oldPosition[1] = a[1];
        backGround.repaint();
        backGround.setBackground(Color.black);
        ende = game.getEnde();
        if (ende) {
            sound.winBig();
            JOptionPane.showMessageDialog(null, "Sie haben das Ziel erreicht.", "Maze", JOptionPane.INFORMATION_MESSAGE);
        }
        frame.requestFocus();
    }

    /**
     * loads the maze of the game
     */
    public void resetBoard() {
        try {
            frame.remove(backGround);
        } catch (Exception x) {
            System.out.println("asd");
        }
        fields = new MazeObject[height][lenght];
        backGround = new JPanel();
        backGround = new JPanel(new GridLayout(height, lenght));
        boolean[][] a = game.getMaze();
        for (int i = 0; i < height; i++) {
            for (int index = 0; index < lenght; index++) {
                if (a[i][index]) {
                    fields[i][index] = new Way();
                } else {
                    fields[i][index] = new Wall();
                }
                backGround.add(fields[i][index]);
            }
        }
        frame.add(backGround);
        frame.setSize(lenght * 10 + 50, height * 10 + 100);
        fields[height - 1][lenght - 1].setBackground(Color.yellow);
        updateBoard();
        gestartet = true;
        frame.add(backGround);
    }

    @Override
    public void onStateChange(KeyboardListener.ArrowDirection direction) {
        if (!ende && gestartet) {
            switch (direction) {
                case Down:
                case Up:
                case Left:
                case Right:
                    game.move(direction);
                    updateBoard();
                    break;
            }
        }
    }
}
