package old.games_fx.DefaultGrapics.IndexButton;

/**
 * @author Elias Schorr, BBBaden
 * @since 25.09.2014
 * @version 1.0
 */
public interface ClickObserver {

    /**
     *
     * @param clicked
     * @param xKord
     * @param yKord
     */
    public void onStateChange(Click clicked, int xKord, int yKord);
}
