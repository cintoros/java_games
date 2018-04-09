package old.maze_fx.maze.models;

/**
 * defines the typ/status of an field
 *
 * @author Elias Schorr
 * @since 21.01.2015
 * @version 1.0
 */
public enum FieldTyp {

    /**
     * It is hidden
     */
    HIDDEN,
    /**
     * there is an way
     */
    WAY,
    /**
     * there is a wall
     */
    WALL,
    /**
     * the player stands on it
     */
    PLAYER,
    /**
     * it is the old.maze_fx.start point
     */
    START,
    /**
     * it is the goal
     */
    GOAL
}
