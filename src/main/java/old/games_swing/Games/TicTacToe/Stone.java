package old.games_swing.Games.TicTacToe;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * a TicTacToe Stone that can be empty or filled with an cross or an oval
 *
 * @author Elias
 */
public class Stone extends JPanel {

    private Forms form;//saves the actual form

    /**
     * creates an new empty stone
     */
    public Stone() {
        this.form = Forms.Empty;
        this.setBorder(new BevelBorder(0));
    }

    /**
     * the Forms of the stones that are possible
     */
    public enum Forms {

        Oval, Cross, Empty
    }

    /**
     * This methode draws the object that is given, when the Panel is still
     * empty
     *
     * @param player
     * @return
     */
    public boolean drawCross(Forms player) {
        if (form == Forms.Empty) {
            form = player;
        } else {
            return false;
        }
        repaint();
        return true;
    }

    /**
     * @return returns the actuell form of this stone
     */
    public Forms getForm() {
        return form;
    }

    /**
     * resets the Panel to empty
     */
    public void reset() {
        form = Forms.Empty;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {//Paint methode
        g.setColor(Color.red);//setting the color in wich it should be painted
        if (form == Forms.Cross) {
            g.drawLine(5, 5, getWidth() - 5, getHeight() - 5);//painting an cross
            g.drawLine(getWidth() - 5, 5, 5, getHeight() - 5);
        } else if (form == Forms.Oval) {
            g.drawOval(5, 5, getWidth() - 10, getHeight() - 10);//painting an oval
        }
    }
}
