package old.games_swing.Games.fourInRow;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * the Stone class that paintes an stone on an blue background in white,red or
 * yellow
 *
 * @author Elias
 */
public class Stone extends JPanel {

    Color thisColor;//to save the actual Color of the Panel

    /**
     * crates an new empty (white) Stone
     */
    public Stone() {
        this.thisColor = Color.white;
        setBackground(Color.blue);
        repaint();
    }

    /**
     * all allowed colors of the stones (Red, Yellow or emty (white))
     */
    public enum StoneColor {

        Red, Yellow, White
    }

    /**
     * draws the stone in the color that is given
     *
     * @param color
     */
    public void drawStone(StoneColor color) {
        switch (color) {
            case Red: {
                thisColor = Color.red;
                break;
            }
            case Yellow: {
                thisColor = Color.yellow;
                break;
            }
            case White: {
                thisColor = Color.white;
                break;
            }
        }
        repaint();//repaints the Object with the new color
    }

    @Override
    protected void paintComponent(Graphics g) {//Paint methode
        g.setColor(thisColor);//setting the color in wich it should be painted
        g.fillOval(10, 10, this.getWidth() - 20, this.getHeight() - 20);//painting an filled oval
    }
}
