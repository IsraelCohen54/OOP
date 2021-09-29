import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * the class run multiplyed frames and two separate rectangle windows with balls.
 */
public class BouncingBallAnimation {
    /**
     * the method run one bouncing ball.
     *
     * @param args = string
     */
    public static void main(String[] args) {
        int xhorizonBound = 200;
        int yverticalBound = 200;
        GUI gui = new GUI("title", xhorizonBound, yverticalBound);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK, xhorizonBound, yverticalBound);
        ball.setVelocity(5, 5);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}