package old.games_swing.Games.Maze.Maze_3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JPanel;

/**
 * @author Elias
 * @school BBBaden
 * @created 02.09.2014
 * @version 1.0
 */
public class DrawPanel extends JPanel {

    private boolean surroundings[][];

    /**
     * surroundings are set to false
     */
    public DrawPanel() {
        this.surroundings = new boolean[3][3];
    }

    /**
     * sets the old.maze_fx.start value of the surroundings and paints them
     *
     * @param surroundings the old.maze_fx.start value of the surroundings
     */
    public DrawPanel(boolean[][] surroundings) {
        this.surroundings = surroundings;
    }

    /**
     * alters the surroundings and activates an repaint
     *
     * @param surroundings
     */
    public void setSurroundings(boolean[][] surroundings) {
        this.surroundings = surroundings;
        repaint();
    }

    /**
     * draws the whole graphic depending on the surroundings array
     *
     * @param g the graphics
     */
    @Override
    public void paintComponent(final Graphics g) {
        int height = this.getSize().height;
        int width = this.getSize().width;
        this.setBackground(Color.white);
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        //filling des backgrounds
        g2d.setPaint(Color.lightGray);
        Polygon p = new Polygon();//up celling
        p.addPoint(0, 0);
        p.addPoint(width, 0);
        p.addPoint(width / 5 * 3, height / 5 * 2);
        p.addPoint(width / 5 * 2, height / 5 * 2);
        g.fillPolygon(p);//celling added
        p = new Polygon();//up celling
        p.addPoint(0, height);
        p.addPoint(width, height);
        p.addPoint(width / 5 * 3, height / 5 * 3);
        p.addPoint(width / 5 * 2, height / 5 * 3);
        g.fillPolygon(p);
        //drawing lines
        g2d.setPaint(Color.black);
        g2d.drawLine(width, height / 5, 0, height / 5);
        g2d.drawLine(width, height / 5 * 4, 0, height / 5 * 4);
        g2d.drawLine(width / 5, height / 5 * 3, width / 5 * 4, height / 5 * 3);
        g2d.drawLine(width / 5, height / 5 * 2, width / 5 * 4, height / 5 * 2);
//        g2d.drawLine(0, 0, width, height);
//        g2d.drawLine(0, height, width, 0);
        //drawing background
        g2d.setPaint(Color.lightGray);
        if (!surroundings[0][0]) {//left side
            p = new Polygon();
            p.addPoint(0, 0);
            p.addPoint(0, height);
            p.addPoint(width / 5, height / 5 * 4);
            p.addPoint(width / 5, height / 5);
            g.fillPolygon(p);
        }
        if (!surroundings[1][0]) {//left side ahead
            p = new Polygon();
            p.addPoint(width / 5, height / 5 * 4);
            p.addPoint(width / 5, height / 5);
            p.addPoint(width / 5 * 2, height / 5 * 2);
            p.addPoint(width / 5 * 2, height / 5 * 3);
            g.fillPolygon(p);
        }
        if (!surroundings[0][2]) {//right side
            p = new Polygon();
            p.addPoint(width, 0);
            p.addPoint(width, height);
            p.addPoint(width / 5 * 4, height / 5 * 4);
            p.addPoint(width / 5 * 4, height / 5);
            g.fillPolygon(p);
        }
        if (!surroundings[1][2]) {//right side ahead
            p = new Polygon();
            p.addPoint(width / 5 * 3, height / 5 * 2);
            p.addPoint(width / 5 * 3, height / 5 * 3);
            p.addPoint(width / 5 * 4, height / 5 * 4);
            p.addPoint(width / 5 * 4, height / 5);
            g.fillPolygon(p);
        }
        if (!surroundings[2][1]) {//vordere Wand
            g2d.setPaint(Color.LIGHT_GRAY);
            g2d.fillRect((width / 5) * 2, (height / 5) * 2, (width / 5), (height / 5));
        }
        //drawing the lines / rectangels
        g2d.setPaint(Color.BLACK);
        g2d.drawRect((width / 5), (height / 5), ((width / 5) * 3), ((height / 5) * 3));
        g2d.drawRect((width / 5) * 2, (height / 5) * 2, (width / 5), (height / 5));
        g2d.drawLine(0, 0, (width / 5) * 2, (height / 5) * 2);
        g2d.drawLine(width, 0, (width / 5) * 3, (height / 5) * 2);
        g2d.drawLine(0, height, (width / 5) * 2, (height / 5) * 3);
        g2d.drawLine(width, height, (width / 5) * 3, (height / 5) * 3);
        if (!surroundings[1][1]) {//vordere Wand
            g2d.setPaint(Color.LIGHT_GRAY);
            g2d.fillRect((width / 5), (height / 5), (width / 5) * 3, (height / 5) * 3);
        }
        g2d.setPaint(Color.BLACK);
        g2d.drawRect((width / 5), (height / 5), ((width / 5) * 3), ((height / 5) * 3));
    }
}
