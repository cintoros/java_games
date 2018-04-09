package old.games_swing.Games.Sudoku;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.Sounds.Sounds;
import old.games_swing.Games.Sudoku.Sudoku.Difficulty;
import old.games_swing.GameLogic.TimeCalculation;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * the Gui of the Maze Game
 *
 * @author Elias
 */
public class SudokuGui {

    private final Sounds sound;//classObjects
    private final TimeCalculation time;
    private final Sudoku game;
    private SudokuZahl fields[][];//GuiObjects
    private JPanel backGround;
    private final JLabel triesOut;
    private final DefFrame frame;
    private final JButton newGame, resetGame, checkGame;
    private boolean started;//variables
    private int quadsize, max, tries;

    /**
     * constructor a new MazeGui with all objects and Actions is created
     *
     * @param parent
     */
    public SudokuGui(DefFrame parent) {
        this.triesOut = new JLabel("Versuche: 0");
        this.game = new Sudoku();
        time = new TimeCalculation();
        checkGame = new JButton("prüfen");
        sound = new Sounds();
        frame = parent;
        newGame = new JButton("neues Spiel");
        resetGame = new JButton("Reset");
        parent.addToMenu(newGame);
        parent.addToMenu(resetGame);
        parent.addToMenu(checkGame);
        parent.addToMenu(triesOut);
        parent.addToMenu(time.getChanginglabel());
        newGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newSudoku();
            }
        });
        checkGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (started) {//only checks if the game is started
                    checkSudoku();
                }
            }
        });
        resetGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (started) {//only resets if the game is started
                    reset();
                }
            }
        });
        frame.pack();
        started = false;
        time.start();
    }

    /**
     * resets the player position
     */
    public void reset() {
        tries = 0;
        triesOut.setText("Versuche: " + tries);
        game.reset();
        resetBoard();
    }

    /**
     * creates an new Sudoku with the given variables
     */
    public void newSudoku() {
        Object[] options = {"3er", "4er", "5er", "abbruch"};
        int eingabeInt = JOptionPane.showOptionDialog(null, "Was für ein Sudoku wollen Sie erstellen?", "Sudoku", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (eingabeInt != 3) {
            Object[] options2 = {"sehr leichtes", "leichtes", "normales", "schweres", "sehr schweres", "gottgleiches", "legendäres", "göttliches", "abbruch"};
            int eingabe = JOptionPane.showOptionDialog(null, "Wie schwer wollen Sie es haben?", "Sudoku", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options[0]);
            if (eingabe != 8) {
                Difficulty dif = Difficulty.veryEasy;
                switch (eingabe) {
                    case 0: {
                        dif = Difficulty.veryEasy;
                        break;
                    }
                    case 1: {
                        dif = Difficulty.Easy;
                        break;
                    }
                    case 2: {
                        dif = Difficulty.Normal;
                        break;
                    }
                    case 3: {
                        dif = Difficulty.hard;
                        break;
                    }
                    case 4: {
                        dif = Difficulty.veryhard;
                        break;
                    }
                    case 5: {
                        dif = Difficulty.godlike;
                        break;
                    }
                    case 6: {
                        dif = Difficulty.legend;
                        break;
                    }
                    case 7: {
                        dif = Difficulty.god;
                        break;
                    }
                }
                quadsize = 3 + eingabeInt;
                game.newGame(quadsize, dif);
                started = true;
                max = quadsize * quadsize;
                reset();
            }
        }
    }

    /**
     * <p>
     * checks the Sudolu</p>
     * <p>
     * imports the values of the actual Sudoku and gives it to the Game to look
     * if the Sudoku is right</p>
     */
    public void checkSudoku() {
        int[][] player = new int[max][max];
        for (int i = 0; i < max; i++) {
            for (int index = 0; index < max; index++) {
                player[i][index] = fields[i][index].getNumber();
            }
        }
        boolean[][] sudokuCorrect = game.isCorrect(player);
        if (game.getEnde()) {
            sound.winBig();
            JOptionPane.showMessageDialog(null, "Bravo Sie haben das Suduko gelöst!", "Sudoku", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (int i = 0; i < max; i++) {
                for (int index = 0; index < max; index++) {
                    fields[i][index].setNumberWasTrue(sudokuCorrect[i][index]);
                }
            }
            tries++;
            triesOut.setText("Versuche: " + tries);
        }
    }

    /**
     * loads the maze of the game
     */
    public void resetBoard() {
        try {
            frame.remove(backGround);
        } catch (Exception x) {
        }
        fields = new SudokuZahl[max][max];//background is being prepared
        backGround = new JPanel(new GridLayout(max, max));
        int[][] a = game.getSudoku();
        for (int i = 0; i < max; i++) {//numbers are filled in
            for (int index = 0; index < max; index++) {
                boolean enab;
                enab = a[i][index] == 0;
                fields[i][index] = new SudokuZahl(a[i][index], enab, max);
                int xBoarder = 1, yBoarder = 1;
                if ((i + 1) % quadsize == 0) {//if the border is on the end of a quadrat it is thicker
                    xBoarder = 3;
                }
                if ((index + 1) % quadsize == 0) {
                    yBoarder = 3;
                }//seeting the border and adding it to the background
                fields[i][index].setBorder(BorderFactory.createMatteBorder(1, 1, xBoarder, yBoarder, Color.black));
                backGround.add(fields[i][index]);
            }
        }
        frame.add(backGround);//frame adds background
    }
}
