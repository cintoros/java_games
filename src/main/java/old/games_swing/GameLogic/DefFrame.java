package old.games_swing.GameLogic;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;

/**
 * creates an JFrame with the given height, width and titel. The Frame is
 * Resizable, it has an battlefield icon and it disposes on close.
 *
 * @author Elias
 */
public class DefFrame extends javax.swing.JFrame {

    private final JMenuBar menu;//menubar of the frame

    /**
     * creates an new JFrame with the given dates
     *
     * @param width
     * @param height
     * @param titel
     */
    public DefFrame(int width, int height, String titel) {
        setPreferredSize(new Dimension(width, height));
        setSize(width, height);
        setResizable(false);
        setTitle(titel);
        Image icon = new ImageIcon(this.getClass().getResource("battlefield.png")).getImage();
        setIconImage(icon);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        menu = new JMenuBar();
        setJMenuBar(menu);
        menu.setLayout(new FlowLayout());
    }

    /**
     * this method adds the given Component to the menu of this Frame
     *
     * @param object
     */
    public void addToMenu(Component object) {
        menu.add(object);
    }

    /**
     * this method removes the given Component from the menu of this Frame
     *
     * @param object
     */
    public void removeFromMenu(Component object) {
        menu.remove(object);
    }
}
