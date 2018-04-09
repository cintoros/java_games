package old.games_fx.GameLogic.Queries;

/**
 * @author Elias Schorr, BBBaden
 * @since 05.10.2014
 * @version 1.0
 */
public interface Queries {

    /**
     * Options to choose
     */
    public enum options {

        /**
         * approved
         */
        YES,
        /**
         * not approved
         */
        NO,
        /**
         * cancelled
         */
        CANCEL
    }

    /**
     *
     * @param question
     * @param title
     * @return
     */
    public options askYesNo(String question, String title);

    /**
     *
     * @param message
     * @param title
     */
    public void showMessage(String message, String title);

    /**
     * gives back the heigth and the length
     *
     * @param min the minimal size
     * @param max the maximal size
     * @param titel the titel of the Programm
     * @return gives back the heigt and length
     */
    public int[] askNewSizes(int min, int max, String titel);

    /**
     * gives back the size between(including) the min and max
     *
     * @param sizeName the name of the Size
     * @param min the minimal size
     * @param max the maximal size
     * @param titel the titel of the Programm
     * @return gives back the size
     */
    public int askSize(String sizeName, int min, int max, String titel);

    /**
     * asks the user the question and returns the answer as String
     *
     * @param question the message
     * @param titel the titel of the Programm
     * @return gives back the answer
     */
    public String askSomething(String question, String titel);
}
