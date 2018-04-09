package old.games_swing.Games.Memory;

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * This class calculates the pairs, the colors, the numbers and the images of
 * the memory
 *
 * @author Elias
 */
public class MemoryBerechnung {

    private final ImageIcon[] images;
    private final Color[] farben;//array for the colors of the memory

    /**
     * when the constructor is loaded the colors are being calculated
     */
    public MemoryBerechnung() {
        images = new MemoryBilder().getImages();
        farben = new Color[125];
        int zaehler = 0;
        for (int r = 0; r < 5; r++) {
            for (int g = 0; g < 5; g++) {
                for (int b = 0; b < 5; b++) {
                    farben[zaehler] = new Color(r * 50, g * 50, b * 50);
                    zaehler++;
                }
            }
        }
    }

    /**
     * gives back an Array of Colors for the memory
     *
     * @param anzahlPaare
     * @return
     */
    public Color[] getColors(int anzahlPaare) {
        Color col[] = new Color[anzahlPaare];
        System.arraycopy(this.farben, 0, col, 0, anzahlPaare);
        return col;
    }

    /**
     * gives back an array twice the amount of pairs (the amount of cards),there
     * are numbers between 0 and the maximum of pairs each nubmer exists only
     * once
     *
     * @param anzahlPaare
     * @return
     */
    public int[] calculateMemory(int anzahlPaare) {
        int[] hallo = new int[anzahlPaare * 2];
        for (int i = 0; i < anzahlPaare; i++) {
            hallo[i] = 0;
            hallo[i + anzahlPaare] = 0;
        }
        for (int i = 0; i < anzahlPaare; i++) {
            while (true) {
                int a = ((int) (Math.random() * anzahlPaare * 2));
                if (hallo[a] == 0) {
                    hallo[a] = i;
                    break;
                }
            }
            while (true) {
                int a = ((int) (Math.random() * anzahlPaare * 2));
                if (hallo[a] == 0) {
                    hallo[a] = i;
                    break;
                }
            }
        }
        return hallo;
    }

    /**
     * gives back an copy of the images
     *
     * @param anzahlPaare
     * @return
     */
    public ImageIcon[] getImages(int anzahlPaare) {
        ImageIcon[] bilder = new ImageIcon[anzahlPaare];
        System.arraycopy(images, 0, bilder, 0, anzahlPaare);
        return bilder;
    }

    /**
     * gives back an array of numbers
     *
     * @param anzahlPaare
     * @return
     */
    public int[] getZahlen(int anzahlPaare) {
        int[] zahlen = new int[anzahlPaare];
        for (int i = 0; i < anzahlPaare; i++) {
            zahlen[i] = i;
        }
        return zahlen;
    }
}
