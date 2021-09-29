import java.awt.Color;

import biuoop.DrawSurface;

/**
 * This class represents a lives indicator object.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;
    private Rectangle rectangle;

    /**
     * construct a lives indicator from
     * a given lives counter object.
     *
     * @param lives the given lives counter.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
        this.rectangle = new Rectangle(new Point(0, 0), 200, 15);
    }

    /**
     * this method draws the lives indicator on given DrawSurface.
     *
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getx(),
                (int) this.rectangle.getUpperLeft().gety(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawText((int) (this.rectangle.getUpperLeft().getx()
                        + this.rectangle.getWidth() / 2 - 20),
                (int) (this.rectangle.getUpperLeft().gety()
                        + this.rectangle.getHeight() / 2 + 5),
                "Lives: "
                        + Integer.toString(this.lives.getValue()), 13);
    }

    /**
     * this method notifies the lives indicator that a time unit has passed.
     */
    public void timePassed() {
    }

    /**
     * this method adds the lives indicator to a game.
     *
     * @param game the game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}