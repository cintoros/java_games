package old.games_swing.Games.Memory;

import javax.swing.ImageIcon;

/**
 *
 * @author Elias
 */
public class MemoryBilder {

    private final ImageIcon[] images;

    /**
     * imports the Images
     */
    public MemoryBilder() {
        this.images
                = new ImageIcon[]{new ImageIcon(this.getClass().getResource("./images/50.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/49.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/48.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/47.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/46.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/45.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/44.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/43.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/42.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/41.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/40.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/39.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/38.png")),
                    new ImageIcon(this.getClass().getResource("./images/37.png")),
                    new ImageIcon(this.getClass().getResource("./images/36.png")),
                    new ImageIcon(this.getClass().getResource("./images/35.png")),
                    new ImageIcon(this.getClass().getResource("./images/34.png")),
                    new ImageIcon(this.getClass().getResource("./images/33.png")),
                    new ImageIcon(this.getClass().getResource("./images/32.png")),
                    new ImageIcon(this.getClass().getResource("./images/31.png")),
                    new ImageIcon(this.getClass().getResource("./images/30.png")),
                    new ImageIcon(this.getClass().getResource("./images/29.png")),
                    new ImageIcon(this.getClass().getResource("./images/28.png")),
                    new ImageIcon(this.getClass().getResource("./images/27.png")),
                    new ImageIcon(this.getClass().getResource("./images/26.png")),
                    new ImageIcon(this.getClass().getResource("./images/25.png")),
                    new ImageIcon(this.getClass().getResource("./images/24.png")),
                    new ImageIcon(this.getClass().getResource("./images/23.png")),
                    new ImageIcon(this.getClass().getResource("./images/22.png")),
                    new ImageIcon(this.getClass().getResource("./images/21.png")),
                    new ImageIcon(this.getClass().getResource("./images/20.png")),
                    new ImageIcon(this.getClass().getResource("./images/19.png")),
                    new ImageIcon(this.getClass().getResource("./images/18.png")),
                    new ImageIcon(this.getClass().getResource("./images/17.png")),
                    new ImageIcon(this.getClass().getResource("./images/16.png")),
                    new ImageIcon(this.getClass().getResource("./images/15.png")),
                    new ImageIcon(this.getClass().getResource("./images/14.png")),
                    new ImageIcon(this.getClass().getResource("./images/13.png")),
                    new ImageIcon(this.getClass().getResource("./images/12.png")),
                    new ImageIcon(this.getClass().getResource("./images/11.png")),
                    new ImageIcon(this.getClass().getResource("./images/10.png")),
                    new ImageIcon(this.getClass().getResource("./images/9.png")),
                    new ImageIcon(this.getClass().getResource("./images/8.png")),
                    new ImageIcon(this.getClass().getResource("./images/7.png")),
                    new ImageIcon(this.getClass().getResource("./images/6.png")),
                    new ImageIcon(this.getClass().getResource("./images/5.png")),
                    new ImageIcon(this.getClass().getResource("./images/4.png")),
                    new ImageIcon(this.getClass().getResource("./images/3.png")),
                    new ImageIcon(this.getClass().getResource("./images/2.png")),
                    new ImageIcon(this.getClass().getResource("./images/1.png")),
                    new ImageIcon(this.getClass().getResource("./images/51.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/52.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/53.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/54.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/55.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/56.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/57.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/58.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/59.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/60.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/61.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/62.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/63.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/64.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/65.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/66.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/67.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/68.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/69.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/70.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/71.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/72.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/73.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/74.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/75.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/76.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/77.jpg")),
                    new ImageIcon(this.getClass().getResource("./images/78.jpg"))};
    }

    /**
     * @return gives back the Images in an Array
     */
    public ImageIcon[] getImages() {
        return images;
    }
}
