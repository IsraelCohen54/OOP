import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> spritesList;

    /**
     * Constructor of SpriteCollection.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        spritesList.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSpr(Sprite s) {
        this.spritesList.remove(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (int i = 0; i < spritesList.size(); ++i) {
            this.spritesList.get(i).timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spritesList) {
            sprite.drawOn(d);
        }
    }
}