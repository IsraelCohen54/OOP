import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class, defined by rectangle.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private Color blockColor;
    private int blockHealth;
    private List<HitListener> hitListeners;

    /**
     * Constructor =  Instantiates a new Block.
     *
     * @param rec the rec
     */
    public Block(Rectangle rec) {
        this.rec = rec;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method notifies the Block that time has passed.
     */
    public void timePassed() {
    }

    /**
     * @return collision rec.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Constructor of Block with Rec and color.
     *
     * @param rec     the rec
     * @param recolor the rec color
     */
    public Block(Rectangle rec, Color recolor) {
        this.rec = rec;
        this.blockColor = recolor;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method draws a ball on a given surface.
     *
     * @param surface is the surface to draw on
     */
    public void drawOn(DrawSurface surface) {

        int upperX = (int) rec.getUpperLeft().getx();
        int upperY = (int) rec.getUpperLeft().gety();
        int width = (int) rec.getWidth();
        int height = (int) rec.getHeight();

        surface.setColor(this.blockColor);
        surface.fillRectangle(upperX, upperY, width, height);
        surface.setColor(Color.black);
        surface.drawRectangle(upperX, upperY, width, height);
        surface.setColor(Color.white);

        int x = (int) (rec.getUpperLeft().getx() + rec.getWidth() / 2 - 4);
        int y = (int) (rec.getUpperLeft().gety() + rec.getHeight() / 2 + 5);

        if (this.blockHealth > 0) {
            surface.drawText(x, y, Integer.toString(this.blockHealth), 15);
        } else {
            surface.drawText(x, y, "x", 15);
        }

    }

    /**
     * Is close boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean isClose(double x, double y) {
        if (Math.abs(x - y) < 0.01) {
            return true;
        }
        return false;
    }

    /**
     * the method change velocity according to hit an object than change acorrdingly direction by right angle.
     *
     * @param hitter          = for the ball.
     * @param collisionPoint  - of the ball and specific rectangle
     * @param currentVelocity of the ball
     * @return new velocity after collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double velx = currentVelocity.getvelx();
        double vely = currentVelocity.getvely();
        double velx1 = velx;
        double vely1 = vely;
        Rectangle thecolisrec = getCollisionRectangle();
        double widrec = thecolisrec.getWidth();
        double heigrec = thecolisrec.getHeight();
        if (((isClose(collisionPoint.gety(), thecolisrec.getUpperLeft().gety() + heigrec))
                || (isClose(collisionPoint.gety(), thecolisrec.getUpperLeft().gety())))
                && ((isClose(collisionPoint.getx(), thecolisrec.getUpperLeft().getx() + widrec))
                || (isClose(collisionPoint.getx(), thecolisrec.getUpperLeft().getx())))) {
            velx = -velx;
            vely = -vely;
            this.blockHealth -= 1;
        } else {
            if ((collisionPoint.getx() < thecolisrec.getUpperLeft().getx() + widrec)
                    && (collisionPoint.getx() > thecolisrec.getUpperLeft().getx())) {
                vely = -vely;
                this.blockHealth -= 1;
            } else {
                if ((collisionPoint.gety() < thecolisrec.getUpperLeft().gety() + heigrec)
                        && (collisionPoint.gety() > thecolisrec.getUpperLeft().gety())) {
                    velx = -velx;
                    this.blockHealth -= 1;
                }
            }
        }
        this.notifyHit(hitter);
        //now lets change the dx,dy if the ball hit one of the rect corners:
        if (velx1 == velx && vely1 == vely) {
            Velocity newvel = new Velocity(-velx, -vely);
            return newvel;
        }
        Velocity newvel = new Velocity(velx, vely);
        return newvel;
    }

    /**
     * setting color of the block.
     *
     * @param col = get the col and set it i this.color
     */
    public void setBlockColor(Color col) {
        this.blockColor = col;
    }

    /**
     * Set block health.
     *
     * @param hit = changed health by hit
     */
    public void setBlockHealth(int hit) {
        this.blockHealth = hit;
    }

    /**
     * Get block health.
     *
     * @return the block health
     */
    public int getBlockHealth() {
        return this.blockHealth;
    }

    /**
     * This method adds a Block to a game.
     *
     * @param game is the game to add to
     */
    public void addBlockToTheGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Constructor by point width and height.
     *
     * @param upperLeft index of starting point of the block
     * @param width     = width from start point of the block
     * @param height    = height from start
     */
    public Block(Point upperLeft, double width, double height) {
        this.rec = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Remove from game.
     *
     * @param game - removing block from game.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this); //TODO check if its right.
    }

    /**
     * Notify a hit.
     * will be caled whenever a hit occurs.
     *
     * @param hitter the hitter
     */
    private void notifyHit(Ball hitter) {
// Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}