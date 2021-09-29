/**
 * this class represents a ball remover.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter numberOfBallsToRemove;

    /**
     * construct a ball remover from a game and and counter of balls.
     *
     * @param game                  a game to remove balls from.
     * @param numberOfBallsToRemove the number of balls to remove.
     */
    public BallRemover(Game game, Counter numberOfBallsToRemove) {
        this.game = game;
        this.numberOfBallsToRemove = numberOfBallsToRemove;
    }

    /**
     * this method is called whenever a ball is hitting the death region.
     * the method removes the ball from the game and updates the number of
     * balls that left in the game.
     *
     * @param beingHit the block that is being hit (the death region).
     * @param hitter   the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.numberOfBallsToRemove.decrease(1);
    }
}