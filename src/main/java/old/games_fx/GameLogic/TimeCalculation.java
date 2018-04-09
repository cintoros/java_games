package old.games_fx.GameLogic;

import old.games_fx.DefaultGrapics.ChangingLabel.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * This Class is for calculating/saving the time a Programm is started and
 * giving it back on a JLabel.
 *
 * @author Elias Schorr, BBBaden
 * @since 05.10.2014
 * @version 2.0
 */
public class TimeCalculation extends AbstractChangeSubject {

    private final Timeline timeCounter;
    private int seconds, minutes, hours;

    /**
     * new Timer is created that siôunts the time
     */
    public TimeCalculation() {
        timeCounter = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> doSomething()));
        timeCounter.setCycleCount(Animation.INDEFINITE);
    }

    private void doSomething() {
        seconds++;
        if (seconds >= 60) {
            seconds = 0;
            minutes++;
            if (minutes >= 60) {
                minutes = 0;
                hours++;
            }
        }
        setText();
    }

    /**
     * starts or continues the time
     */
    public void start() {
        try {
            timeCounter.play();
        } catch (Exception ex) {
        }
    }

    /**
     * stops/pauses the time
     */
    public void stop() {
        try {
            timeCounter.stop();
        } catch (Exception ex) {
        }
    }

    /**
     * resets the time to 0
     */
    public void reset() {
        seconds = 0;
        minutes = 0;
        hours = 0;
        setText();
    }

    /**
     * @return gives back the actual time of the timer in seconds
     */
    public int getTimeSec() {
        return seconds + (((hours * 60) + minutes) * 60);
    }

    /**
     * sets the time
     *
     * @param s
     */
    public void setTime(int s) {
        minutes = s / 60;
        seconds = s - (minutes * 60);
        hours = minutes / 60;
        minutes = minutes - (hours * 60);
        setText();
    }

    /**
     * sets the text of the observers
     */
    private void setText() {
        this.notifyObservers(getTime());
    }

    /**
     *
     * @return
     */
    public String getTime() {
        return "Zeit: " + hours + ":" + minutes + ":" + seconds;
    }

    /**
     * updates the time
     */
    public void updateTime() {
        setText();
    }
}
