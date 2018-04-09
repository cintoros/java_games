package old.games_swing.Games.TicTacToe;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.GameLogic.ScoreCounter;
import old.games_swing.Sounds.Sounds;
import old.games_swing.Games.TicTacToe.Stone.Forms;
import old.games_swing.GameLogic.TimeCalculation;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * the classic tic tac toe
 *
 * @author Elias
 */
public class TicTacToe {

    private final JPanel background;
    private final Stone tictactoes[][];//Tic Tac Toe Stones
    private final JButton reset;
    private final DefFrame frame;
    private final TimeCalculation time;
    private final ScoreCounter score;
    private final Sounds sound;
    private Forms player;//saves witch player is on the move
    private boolean end;

    /**
     * creates an new Tic Tac Toe Game
     *
     * @param parent
     */
    public TicTacToe(DefFrame parent) {
        this.player = Stone.Forms.Oval;
        frame = parent;
        sound = new Sounds();
        score = new ScoreCounter(2);
        time = new TimeCalculation();
        this.background = new JPanel(new GridLayout(3, 3));
        reset = new JButton("Reset");
        frame.addToMenu(reset);
        frame.addToMenu(time.getChanginglabel());
        frame.addToMenu(score.getchangingLabel());
        this.tictactoes = new Stone[3][3];
        for (int x = 0; x < 3; x++) {//adding action for Tic Tac Toe
            for (int y = 0; y < 3; y++) {
                tictactoes[x][y] = new Stone();
                background.add(tictactoes[x][y]);
                tictactoes[x][y].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (!end) {
                            click(evt);
                        }
                    }
                });
            }
        }
        reset.addMouseListener(new java.awt.event.MouseAdapter() {//vreates an new Tic Tac Toe
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reset();
            }
        });
        frame.add(background);
        end = false;
        time.start();
    }

    private void click(MouseEvent evt) {//action when an Stone is clicked
        Stone button = (Stone) evt.getSource();
        if (button.drawCross(player)) {//trying to draw a Cross
            background.repaint();
            if (hasWon()) {
                winPlayer();
            }
            if (player == Forms.Oval) {
                player = Forms.Cross;
            } else {
                player = Forms.Oval;
            }
        } else {
            sound.lose();//if ther is something play lose
        }

    }

    /**
     * @return returns if the player has won
     */
    public boolean hasWon() {
        end = true;
        for (int i = 0; i < 3; i++) {
            if (tictactoes[0][i].getForm() == player && tictactoes[1][i].getForm() == player && tictactoes[2][i].getForm() == player) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tictactoes[i][0].getForm() == player && tictactoes[i][1].getForm() == player && tictactoes[i][2].getForm() == player) {
                return true;
            }
        }
        if (tictactoes[0][0].getForm() == player && tictactoes[1][1].getForm() == player && tictactoes[2][2].getForm() == player) {
            return true;
        }
        if (tictactoes[2][0].getForm() == player && tictactoes[1][1].getForm() == player && tictactoes[0][2].getForm() == player) {
            return true;
        }
        end = false;
        return false;
    }

    /**
     * resets the game field
     */
    public void reset() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tictactoes[x][y].reset();
            }
        }
        end = false;
        background.repaint();
    }

    private void winPlayer() {//when a player has won it is given out wich pllayer and his score increasses
        sound.winBig();
        if (player == Forms.Oval) {
            score.addPoints(1, 1);
            JOptionPane.showMessageDialog(null, "Spieler 1 hat gewonnen!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
        } else {
            score.addPoints(2, 1);
            JOptionPane.showMessageDialog(null, "Spieler 2 hat gewonnen!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
