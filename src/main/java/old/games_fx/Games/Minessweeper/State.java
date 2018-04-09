package old.games_fx.Games.Minessweeper;

/**
 * descripes what state(layout) a minessweeper field has and how many bombs
 * there are.
 *
 * @author Elias Schorr, BBBaden
 * @since 02.10.2014
 * @version 1.0
 */
public enum State {

    /**
     * there is nohing
     */
    NONE,
    /**
     * there is a bomb
     */
    BOMB,
    /**
     * there is a flag
     */
    FLAG,
    /**
     * there is no bomb
     */
    NOBOMB,
    /**
     * there is one bomb around
     */
    ONE,
    /**
     * there are two bomb around
     */
    TWO,
    /**
     * there are three bomb around
     */
    THREE,
    /**
     * there are four bomb around
     */
    FOUR,
    /**
     * there are five bomb around
     */
    FIVE,
    /**
     * there are six bomb around
     */
    SIX,
    /**
     * there are seven bomb around
     */
    SEVEN,
    /**
     * there are eight bomb around
     */
    EIGHT
}
