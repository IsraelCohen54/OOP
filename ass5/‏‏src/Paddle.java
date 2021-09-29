import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private static int paddlWidth = 100;
    private static int paddlHeight = 3;
    private Rectangle rect;
    private int windowWidth = 800;


    /**
     * Paddle constructor.
     *
     * @param windowWidth  window paddlWidth
     * @param windowHeight window paddlHeight
     * @param gui          = the gui interface
     */
    public Paddle(int windowWidth, int windowHeight, GUI gui) {
        this.windowWidth = windowWidth;
        Point p = new Point((this.windowWidth - paddlWidth) / 2,
                windowHeight - 24);
        this.rect = new Rectangle(p, paddlWidth, paddlHeight);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * move left.
     */
    public void moveLeft() {
        double x, y;
        x = this.rect.getUpperLeft().getx() - 5;
        y = this.rect.getUpperLeft().gety();
        if (x < 20) {
            x = 20;
        }
        this.rect = new Rectangle(new Point(x, y), paddlWidth, paddlHeight);
    }

    /**
     * Move right.
     */
    public void moveRight() {
        double x, y;
        x = this.rect.getUpperLeft().getx() + 5;
        y = this.rect.getUpperLeft().gety();
        if (x + paddlWidth >= this.windowWidth - 20) {
            x = this.windowWidth - this.rect.getWidth() - 20;
        }
        this.rect = new Rectangle(new Point(x, y), paddlWidth, paddlHeight);
    }

    /**
     * notify the paddle that time passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        int xVal = (int) rect.getUpperLeft().getx();
        int yVal = (int) rect.getUpperLeft().gety();
        int wid = (int) rect.getWidth();
        int hei = (int) rect.getHeight();

        d.setColor(Color.yellow);
        d.fillRectangle(xVal, yVal, wid, hei);
        d.setColor(Color.black);
        d.drawRectangle(xVal, yVal, wid, hei);
    }

    /**
     * the method return the collided rectangle.
     *
     * @return collided rec
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          - the hitting ball.
     * @return velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddlXpoint = this.rect.getUpperLeft().getx();
        double paddleParts = paddlWidth / 5;
        double collisPointX = collisionPoint.getx();
        Velocity k;
//from left most region of the paddle goes to the right:
        if (collisPointX >= paddlXpoint && collisPointX <= paddleParts + paddlXpoint) {

            k = Velocity.fromAngleAndSpeed(-60, 4);
            if (k.getvely() > 0) {
                k.setvely(-k.getvely());
            }
            return k;
        }
        if (collisPointX > paddleParts + paddlXpoint && collisPointX <= paddleParts * 2 + paddlXpoint) {
            k = Velocity.fromAngleAndSpeed(330, 4);
            if (k.getvely() > 0) {
                k.setvely(-k.getvely());
            }
            return k;
        }
        if (collisPointX > 2 * paddleParts + paddlXpoint && collisPointX <= paddleParts * 3 + paddlXpoint) {
            return new Velocity(currentVelocity.getvelx(), -currentVelocity.getvely());
        }
        if (collisPointX > 3 * paddleParts + paddlXpoint && collisPointX <= paddleParts * 4 + paddlXpoint) {
            k = Velocity.fromAngleAndSpeed(30, 4);
            if (k.getvely() > 0) {
                k.setvely(-k.getvely());
                return k;
            }
        }
        if (collisPointX > 4 * paddleParts + paddlXpoint && collisPointX <= paddleParts * 5 + paddlXpoint) {
            k = Velocity.fromAngleAndSpeed(60, 4);
            if (k.getvely() > 0) {
                k.setvely(-k.getvely());
                return k;
            }
        }
        return currentVelocity;
    }

    /**
     * Adding paddle to the game.
     *
     * @param g the g
     */
    public void addPadToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}