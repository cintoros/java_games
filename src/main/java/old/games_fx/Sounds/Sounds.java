package old.games_fx.Sounds;

import java.applet.Applet;
import java.applet.AudioClip;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * This class is for playing diffrent kind of sounds. when the methods are first
 * called the AudioClip is loaded and played after that. Second thime the Clips
 * are only played
 *
 * @author Elias
 */
public class Sounds {

    private AudioClip winLittle, winBig, lose, ping, hello, boom, backGround;

    /**
     * plays a win sound for little Wins
     */
    public void winLittle() {
        try {
            if (winLittle == null) {
                winLittle = Applet.newAudioClip(this.getClass().getResource("tada.wav"));
            }
            winLittle.play();
        } catch (Exception e) {
            System.out.println("Fehler: Audio-Datei nicht gefunden");
        }
    }

    /**
     * plays a win sound for Big Wins
     */
    public void winBig() {
        try {
            if (winBig == null) {
                winBig = Applet.newAudioClip(this.getClass().getResource("tada.wav"));
            }
            winBig.play();
        } catch (Exception e) {
            System.out.println("Fehler: Audio-Datei nicht gefunden");
        }
    }

    /**
     * plays a stop sound
     */
    public void lose() {
        try {
            if (lose == null) {
                lose = Applet.newAudioClip(this.getClass().getResource("stop.wav"));
            }
            lose.play();
        } catch (Exception e) {
            System.out.println("Fehler: Audio-Datei nicht gefunden");
        }
    }

    /**
     * plays a ping sound
     */
    public void ping() {
        try {
            if (ping == null) {
                ping = Applet.newAudioClip(this.getClass().getResource("chord.wav"));
            }
            ping.play();
        } catch (Exception e) {
            System.out.println("Fehler: Audio-Datei nicht gefunden");
        }
    }

    /**
     * plays a ping sound to welcome the user
     */
    public void hello() {
        try {
            if (hello == null) {
                hello = Applet.newAudioClip(this.getClass().getResource("ping.wav"));
            }
            hello.play();
        } catch (Exception e) {
            System.out.println("Fehler: Audio-Datei nicht gefunden");
        }
    }

    /**
     * an explosion occurs
     */
    public void boom() {
        try {
            if (boom == null) {
                boom = Applet.newAudioClip(this.getClass().getResource("bang.wav"));
            }
            boom.play();
        } catch (Exception e) {
            System.out.println("Fehler: Audio-Datei nicht gefunden");
        }
    }

    /**
     * plays the windows town sound until the programm is stopped
     * the sound is reducesd in the volume that teh other sound can be heard 
     */
    public void backgroundMusic() {
        try {
            //get Music
            backGround = Applet.newAudioClip(this.getClass().getResource("town.mid"));
            backGround.loop();
            //set Volume
            FloatControl gainControl;
            Clip clip = (Clip) backGround;
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20);
        } catch (Exception e) {
            System.out.println("Fehler: Audio-Datei nicht gefunden\n"+e);
        }
    }

    /**
     * stops the background music
     */
    public void stopbackground(){
        try {
        backGround.stop();
        } catch (Exception e) {
            System.out.println("Fehler: Audio-Datei nicht gefunden\n"+e);
        }
    }
}
