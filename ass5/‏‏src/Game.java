import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Game.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Sleeper sleeper;
    private GUI gui;
    private int xHorizonBound = 800;
    private int yVerticalBound = 600;
    private Counter lives;
    private Counter score;
    private Counter remainingBlocks;
    private Counter remainingBalls;

    /**
     * constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.gui = new GUI("Game", xHorizonBound, yVerticalBound);
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(2);
        this.score = new Counter(0);
        this.lives = new Counter(4);
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c - removing collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s - removing sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSpr(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        BlockRemover blockRmv = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRmv = new BallRemover(this, this.remainingBalls);
        ScoreIndicator theScore = new ScoreIndicator(this.score);
        LivesIndicator theLives = new LivesIndicator(this.lives);
        this.addSprite(theLives);
        this.addSprite(theScore);

        // add bounding window blocks to the game
        Block upWinBound = new Block(new Point(0, 20), xHorizonBound, 20);
        Block leftWinBound = new Block(new Point(0, 0), 20, yVerticalBound);
        Block lowWinBound = new Block(
                new Point(0, yVerticalBound - 1), xHorizonBound, 20);
        Block rightWinBound = new Block(
                new Point(xHorizonBound - 20, 20), 20, yVerticalBound);
        upWinBound.setBlockColor(Color.darkGray);
        upWinBound.addBlockToTheGame(this);
        leftWinBound.setBlockColor(Color.darkGray);
        leftWinBound.addBlockToTheGame(this);
        lowWinBound.addBlockToTheGame(this);
        lowWinBound.addHitListener(ballRmv);
        lowWinBound.setBlockColor(Color.darkGray);
        rightWinBound.addBlockToTheGame(this);
        rightWinBound.setBlockColor(Color.darkGray);

        ScoreTrackingListener theNewScore = new ScoreTrackingListener(this.score);
//        LiveIndicator
        for (int i = 0; i < 6; i++) {
            Color c = new Color(40, 30 * i, i * 35);
            for (int j = 0; j < 12 - i; j++) {
                int hit = 1;
                if (i == 0) {
                    hit = 2;
                }
                int blockHeight = 30;
                int blockWidth = 55;
                Point p = new Point(xHorizonBound - 20 - (j + 1) * blockWidth,
                        120 + blockHeight * i);
                Block b = new Block(p, blockWidth, blockHeight);
                b.setBlockHealth(hit);
                b.setBlockColor(c);
                b.addHitListener(theNewScore);
                b.addHitListener(blockRmv);
                this.remainingBlocks.increase(1);
                b.addBlockToTheGame(this);
            }
        }
    }

    /**
     * Run.
     */
    public void run() {
        if (this.lives.getValue() != 0) {
            playOneTurn();
        } else {
            gui.close();
        }
    }


    /**
     * OneTurnOf the game.
     */
// Run the game -- start the animation loop.
    public void playOneTurn() {
        Paddle pad = new Paddle(xHorizonBound, yVerticalBound, this.gui);
        pad.addPadToGame(this);
        Ball ballOne = new Ball(xHorizonBound / 2, 23 + yVerticalBound
                / 2, 5, Color.BLACK, xHorizonBound, yVerticalBound);
        Ball ballTwo = new Ball(xHorizonBound / 2, 23 + yVerticalBound
                / 2, 5, Color.BLACK, xHorizonBound, yVerticalBound);
        ballOne.setVelocity(Velocity.fromAngleAndSpeed(14.5, 4));
        ballTwo.setVelocity(Velocity.fromAngleAndSpeed(25.8, 4));
        ballOne.setGameEnvironment(this.environment);
        ballTwo.setGameEnvironment(this.environment);
        this.addSprite(ballOne);
        this.addSprite(ballTwo);
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.remainingBlocks.getValue() == 0) {
                this.score.increase(100);
                gui.close();
            }
            if (this.remainingBalls.getValue() == 0) {
                this.lives.decrease(1);
                this.remainingBalls.setValue(2);
                this.removeSprite(pad);
                this.removeCollidable(pad);
                run();
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.playOneTurn();
    }
}