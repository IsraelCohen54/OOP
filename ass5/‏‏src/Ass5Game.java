/**
 * Ass5Game class.
 * the main class of this assignment.
 */
public class Ass5Game {
    /**
     * the main method that starts the game.
     *
     * @param args - ignore
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.playOneTurn();
    }
}
